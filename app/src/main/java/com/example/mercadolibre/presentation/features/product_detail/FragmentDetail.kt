package com.example.mercadolibre.presentation.features.product_detail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.mercadolibre.R
import com.example.mercadolibre.core.Constants.ARGUMENT_PRODUCT_ID
import com.example.mercadolibre.core.base.BaseFragment
import com.example.mercadolibre.data.entities.api.ProductPictureResponse
import com.example.mercadolibre.data.entities.dto.ProductDetailDto
import com.example.mercadolibre.data.helpers.ConditionEnum
import com.example.mercadolibre.data.viewmodel.DetailViewModel
import com.example.mercadolibre.databinding.FragmentDetailBinding
import com.example.mercadolibre.presentation.adapters.ImagesSliderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetail : BaseFragment<FragmentDetailBinding>() {
    private val viewModel: DetailViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_detail

    override fun setupView() {
        setupSearchView()
        setupObservers()
        getProductDetail()
    }

    private fun setupObservers() {
        with(viewModel) {
            isLoading.observe(viewLifecycleOwner) { isLoading ->
                showOrHideLoading(isLoading)
            }
            productDetail.observe(viewLifecycleOwner) { product ->
                setProductInfo(product)
            }
            error.observe(viewLifecycleOwner) { errorMessage ->
                showError(errorMessage)
            }
        }
    }

    private fun getProductDetail() {
        viewModel.getProductDetailById(getProductId())
    }

    private fun getProductId(): String {
        return arguments?.getString(ARGUMENT_PRODUCT_ID, "") ?: ""
    }

    private fun setProductInfo(product: ProductDetailDto) {
        with(binding) {
            title.text = product.title
            conditionAndSoldAmount.text = getString(R.string.condition_and_sold_amount, getConditionText(product.condition), product.soldQuantity)
            price.text = getString(R.string.price, product.symbol, product.price.toInt())
            stockTitle.text = getString(R.string.available_stock)
            stockCount.text = getString(R.string.stock_count, product.availableQuantity)
            setupVerifiedSeller(product.officialStoreId != null, product.verifiedSeller ?: "")
            setupSlider(product.pictures)
        }
    }

    private fun setupSlider(pictures: ArrayList<ProductPictureResponse>) {
        with(binding) {
            imageSlider.adapter = ImagesSliderAdapter(requireContext(), pictures)
            imageSlider.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    imageAmountAndIndex.text = getString(R.string.image_amount_and_index, position+1, pictures.size)
                }
                override fun onPageSelected(position: Int) {
                }
            })
            imageAmountAndIndex.text = getString(R.string.image_amount_and_index, 1, pictures.size)
        }
    }

    private fun setupVerifiedSeller(isVerified: Boolean, seller: String) {
        with(binding.verifiedSeller) {
            if(isVerified) {
                apply {
                    visibility = View.VISIBLE
                    text = getString(R.string.detail_seller, seller)
                }
            } else {
                visibility = View.GONE
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setupView { hasFocus ->
            if(hasFocus) {
                goBack()
            }
        }
    }

    private fun getConditionText(condition: String): String {
        return ConditionEnum.valueOf(condition.uppercase()).textToShow
    }

    private fun goBack() {
        findNavController().navigateUp()
    }

}