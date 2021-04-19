package com.target.targetcasestudy.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DealListResponse (  @SerializedName("products")
                               @Expose
                               var products: List<ProductsList>){

    data class ProductsList( var id :Int=0,
                            @SerializedName("title")
                            @Expose
                            var title: String="",
                            @SerializedName("aisle")
                            @Expose
                            var aisle: String="",
                            @SerializedName("description")
                            @Expose
                            var description: String="",
                            @SerializedName("image_url")
                            @Expose
                            var imageUrl: String="",
                            @SerializedName("regular_price")
                            @Expose
                            var regularPrice: RegularPrice?,
                            @SerializedName("sale_price")
                            @Expose
                            var salePrice: SalePrice?) {


       data class RegularPrice(    @SerializedName("amount_in_cents")
                                   @Expose
                                   var amountInCents :Double=0.0,
                                   @SerializedName("currency_symbol")
                                   @Expose
                                  var currencySymbol: String="",
                                   @SerializedName("display_string")
                                   @Expose
                                  var displayString: String="")

        data class SalePrice(  @SerializedName("amount_in_cents")
                               @Expose
                               var amountInCents:Double=0.0,
                               @SerializedName("currency_symbol")
                               @Expose
                               var currencySymbol: String="",
                               @SerializedName("display_string")
                               @Expose
                               var displayString: String="")
    }
}