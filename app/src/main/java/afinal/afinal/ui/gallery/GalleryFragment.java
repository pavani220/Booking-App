package afinal.afinal.ui.gallery;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.afinal.afinal.R;
import com.afinal.afinal.databinding.FragmentGalleryBinding;


public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    // Declare ImageViews and TextViews
    private ImageView imageDronesales, imageSatellite, imageSoiltesting, imageDronespraying, imageAgriadvisory, imageAgirents;
    private TextView textDronesales, textSatellite, textSoiltesting, textDronespraying, textAgriadvisory, textAgirents;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Initialize the ViewModel
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        // Inflate the layout and get the root view
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize ImageViews and TextViews with the correct resource IDs
        imageDronesales = root.findViewById(R.id.image_dronesales);
        imageSatellite = root.findViewById(R.id.image_satellite);
        imageSoiltesting = root.findViewById(R.id.image_soiltesting);
        imageDronespraying = root.findViewById(R.id.image_dronespraying);
        imageAgriadvisory = root.findViewById(R.id.image_agriadvisory);
        imageAgirents = root.findViewById(R.id.image_agirents);
        textDronesales = root.findViewById(R.id.text_dronesales);
        textSatellite = root.findViewById(R.id.text_satellite);
        textSoiltesting = root.findViewById(R.id.text_soiltesting);
        textDronespraying = root.findViewById(R.id.text_dronespraying);
        textAgriadvisory = root.findViewById(R.id.text_agriadvisory);
        textAgirents = root.findViewById(R.id.text_agirents);

        // You can dynamically set text, change images, or handle onClick actions here
        textDronesales.setText("Drone Sales");
        textSatellite.setText("Satellite Soil Testing");
        textSoiltesting.setText("Weather Intelligence");
        textDronespraying.setText("Drone Spraying");
        textAgriadvisory.setText("Agri Advisory");
        textAgirents.setText("Special Agri Services");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
