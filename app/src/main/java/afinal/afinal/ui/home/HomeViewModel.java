package afinal.afinal.ui.home;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.afinal.afinal.R;

public class HomeViewModel extends ViewModel {

    // LiveData to hold the list of image resources
    private final MutableLiveData<int[]> mImages;

    public HomeViewModel() {
        mImages = new MutableLiveData<>();

        // Set the default image list (array of drawable resources)
        mImages.setValue(new int[]{ R.drawable.image2, R.drawable.image3,R.drawable.img,R.drawable.img_5,R.drawable.img});
    }

    // Getter for the LiveData holding the images
    public LiveData<int[]> getImages() {
        return mImages;
    }

    // Setter for the images (if you want to update the image list)
    public void setImages(int[] images) {
        mImages.setValue(images);
    }
}
