package com.samm.brewerysearch.data

interface ModelMapper<R : DataModel, T : ViewDataModel> {
    fun mapperToViewDataModel(dataModel: R): T

    fun mapperToDataModel(viewDataModel: ViewDataModel): DataModel {
        TODO("maybe not implement")

    }
}