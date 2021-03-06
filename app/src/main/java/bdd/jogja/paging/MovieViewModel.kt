package bdd.jogja.paging

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import bdd.jogja.paging.datasource.MovieDataSource
import bdd.jogja.paging.db.Movie


class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val pageSize = 20
    private val isPlaceHolder = true
    private lateinit var movieList: LiveData<PagedList<Movie>>


    fun getAllMovies(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(isPlaceHolder)
            .setInitialLoadSizeHint(pageSize)
            .setPageSize(pageSize)
            .build()
        movieList = initPagedListBuilder(config).build()
        return movieList
    }

    private fun initPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Movie> {

        val dataSourceFactory = object : DataSource.Factory<Int, Movie>() {
            override fun create(): DataSource<Int, Movie> {
                return MovieDataSource()
            }
        }
        return LivePagedListBuilder<Int, Movie>(dataSourceFactory, config)
    }

}