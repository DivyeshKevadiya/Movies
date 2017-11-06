package com.popularmovies.vpaliy.popularmoviesapp.ui.search

import com.popularmovies.vpaliy.domain.interactor.SearchInteractor
import com.popularmovies.vpaliy.domain.interactor.params.Consumer
import com.popularmovies.vpaliy.domain.interactor.params.Response
import com.popularmovies.vpaliy.domain.interactor.params.SearchPage

class SearchPresenter<T>(val search:SearchInteractor<T>):SearchContract.Presenter<T>{

    private lateinit var page:SearchPage
    private lateinit var view: SearchContract.View<T>

    override fun attachView(view: SearchContract.View<T>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun more() {
        page.next()
        search.execute(Consumer(this::onSuccess,this::onError),page)
    }

    override fun query(query: String) {
        page= SearchPage(query)
        search.execute(Consumer(this::onSuccess,this::onError),page)
    }

    override fun stop() {

    }

    private fun onSuccess(response:Response<SearchPage,List<T>>){
        val page=response.request
        if(page.isFirst)
            view.showResult(response.data)
        else
            view.appendResult(response.data)
    }

    private fun onError(ex:Throwable){
        view.error()
    }
}