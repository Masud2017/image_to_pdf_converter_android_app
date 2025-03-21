package com.mksoftwaresolutions.image_to_pdf_converter.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.logging.Logger

class ImageToPdfViewModel:ViewModel() {
    private var logger:Logger = Logger.getLogger("ImageToPdfViewModel")
    private val _pdfGenerationDone:MutableStateFlow<Boolean> = MutableStateFlow(false)
    var pdfGenerationDone = _pdfGenerationDone.asStateFlow()


    fun finishGeneration() {
        this.logger.info("Finishing the generation")
        _pdfGenerationDone.value = true
    }
}