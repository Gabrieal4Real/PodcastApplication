package com.gabrieal.podcastapplication.ui.viewmodel.podcast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gabrieal.podcastapplication.data.api.Resource
import com.gabrieal.podcastapplication.data.api.ResourceError
import com.gabrieal.podcastapplication.data.models.podcast.PodcastListModel
import com.gabrieal.podcastapplication.data.repository.PodcastRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PodcastViewModelImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: PodcastRepository
    private lateinit var viewModel: PodcastViewModelImpl

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        viewModel = PodcastViewModelImpl(repository)
    }

    @Test
    fun `getPodcastList should call repository and update podcastListLiveData`() {
        // Given
        val resourceLiveData = MutableLiveData<Resource<PodcastListModel>>()
        every { repository.getPodcastListFromAPI() } returns resourceLiveData
        
        // When
        viewModel.getPodcastList()
        val testPodcastList = PodcastListModel()
        resourceLiveData.value = Resource.success(testPodcastList)
        
        // Then
        val observed = viewModel.observePodcastList().value
        assertEquals(testPodcastList, observed)
    }

    @Test
    fun `observeLoading should return isLoading LiveData`() {
        // Given
        val observer = mockk<Observer<Boolean>>(relaxed = true)
        
        // When
        viewModel.observeLoading().observeForever(observer)
        
        // Then
        viewModel.observeLoading().value?.let {
            verify { observer.onChanged(it) }
        }
    }

    @Test
    fun `observeError should return isError LiveData`() {
        // Given
        val observer = mockk<Observer<ResourceError?>>(relaxed = true)
        
        // When
        viewModel.observeError().observeForever(observer)
        
        // Then
        viewModel.observeError().value?.let {
            verify { observer.onChanged(it) }
        }
    }

    @Test
    fun `observePodcastList should return podcastListLiveData`() {
        // Given
        val observer = mockk<Observer<PodcastListModel?>>(relaxed = true)
        
        // When
        viewModel.observePodcastList().observeForever(observer)
        
        // Then
        viewModel.observePodcastList().value?.let {
            verify { observer.onChanged(it) }
        }
    }

    @Test
    fun `selectedPodcast should update nowPlayingPodcast`() {
        // Given
        val mp3 = "test.mp3"
        
        // When
        viewModel.selectedPodcast(mp3)
        // Accessing private LiveData via reflection for test
        val field = PodcastViewModelImpl::class.java.getDeclaredField("nowPlayingPodcast")
        field.isAccessible = true
        val liveData = field.get(viewModel) as MutableLiveData<String?>
        
        // Then
        assertEquals(mp3, liveData.value)
    }
}