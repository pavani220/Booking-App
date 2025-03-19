package com.example.afinal.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<String>> timelineItems;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");

        timelineItems = new MutableLiveData<>();
        // Initialize with a default timeline
        timelineItems.setValue(List.of("Hyderabad", "Bangalore", "Telangana", "AP", "Vizag"));
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<String>> getTimelineItems() {
        return timelineItems;
    }

    // You can add methods to update the timeline if needed
    public void updateTimeline(List<String> newTimelineItems) {
        timelineItems.setValue(newTimelineItems);
    }
}
