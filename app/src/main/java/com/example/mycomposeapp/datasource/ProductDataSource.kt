package com.example.mycomposeapp.datasource

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.mycomposeapp.model.ProductModel

class ProductsDataSource {
    fun getAll(): MutableState<List<ProductModel>> {
        val listProducts: MutableList<ProductModel> = mutableListOf()

        listProducts.add(ProductModel("https://http2.mlstatic.com/D_NQ_NP_840187-MLB44830752074_022021-O.webp", "Volkswagen Kombi 1.6 Std 3p Gasolina", 36200.00, true, "São Paulo, SP", (100..1000).random()))
        listProducts.add(ProductModel("https://http2.mlstatic.com/D_NQ_NP_839136-MLB46165664289_052021-O.webp", "Volkswagen Kombi 1.4 mi Std 8v", 33700.00, false, "São Paulo, SP", (100..1000).random()))
        listProducts.add(ProductModel("https://http2.mlstatic.com/D_NQ_NP_688221-MLB46170188215_052021-O.webp", "Volkswagen Kombi 1.4 Total Flex 3p", 36900.00, false, "São Paulo, SP", (100..1000).random()))
        listProducts.add(ProductModel("https://http2.mlstatic.com/D_NQ_NP_935980-MLB45060462456_032021-O.webp", "Kombi Standart", 39900.00, true, "São Paulo, SP", (100..1000).random()))
        listProducts.add(ProductModel("https://http2.mlstatic.com/D_NQ_NP_702290-MLB45835702160_052021-O.webp", "Volkswagen Kombi 1.4 Total Flex 3p", 25000.00, false, "São Paulo, SP", (100..1000).random()))
        listProducts.add(ProductModel("https://http2.mlstatic.com/D_NQ_NP_616706-MLB44777150514_022021-O.webp", "Sucata Vw Kombi 1994 1600", 15000.00, true, "São Paulo, SP", (100..1000).random()))
        listProducts.add(ProductModel("https://http2.mlstatic.com/D_NQ_NP_918658-MLB45998259255_052021-O.webp", "Volkswagen Kombi Kombi 1.6 MI STD LOTAÇÃO 8V GASOLINA 3P MANUAL", 27865.00, false, "São Paulo, SP", (100..1000).random()))


        return mutableStateOf(listProducts)
    }
}



