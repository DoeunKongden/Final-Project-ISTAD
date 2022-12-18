package com.doeunkongden.finalprojectecommerce.ui.Search.adapters;

import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.data.model.api.response.ProductData;

public interface SearchProductClickedListener {
    void onSearhProductClick(ProductAttributes productAttributes,int id);
}
