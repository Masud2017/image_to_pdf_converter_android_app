package com.mksoftwaresolutions.image_to_pdf_converter.services

import java.io.File
/**
 * <h1>DocumentHandler</h1>
 * Document handler handles creation, edition and deletion of a pdf document. It also fetches a list of pdf documents that\
 * has been created by the app only. Means it will put all the pdf file inside a folder called pdf_converter_document; By this
 * It knows where to find for pdf files so that other unnecessary pdfs doesn't get included.
 * */
class DocumentHandler {
    val CREATE_FILE = 1
    val baseUrl = "/image_to_pdf_converter"

    fun createFile(fileData: File) {

    }
    fun createFile(data:String) {

    }

    fun getPdfFileList():Array<File> {
        return emptyArray()
    }

    fun deletePdfFileByName(fileName:String) {}
    fun getPdfFileByName(fileName:String){}
}