package com.carbon.complete.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.carbon.complete.ProfileSetupActivity;
import com.carbon.complete.Utils.Constants;
import com.carbon.complete.Firebase.Logout.LogoutContract;
import com.carbon.complete.Firebase.Logout.LogoutPresenter;
import com.carbon.complete.Firebase.SaveProfilePicture.PhotoInterface;
import com.carbon.complete.Firebase.SaveProfilePicture.PhotoPresenter;
import com.carbon.complete.LoginActivity;
import com.carbon.complete.MainActivity;
import com.carbon.complete.R;
import com.carbon.complete.Utils.FilePath;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import me.tankery.permission.PermissionRequestActivity;

import static android.app.Activity.RESULT_OK;



public class UserProfileFragment extends Fragment implements View.OnClickListener, PhotoInterface.View, LogoutContract.View {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    static final String ARG_PARAM1 = "param1";
    private final int REQUEST_READ_AND_WRITE = 2000;
    private final int GALLERY_REQUEST = 2001;

    private final String TAG = "UserProfileFragment";

    PhotoPresenter mpresenter;


    private String mParam1;
    private String mParam2;

    private Button btn_do;
    private ImageView image_settings;

    private OnFragmentInteractionListener mListener;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment UserProfileFragment.
     */

    public static UserProfileFragment newInstance(String param1) {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        init(view);
        return view;

    }

    private void init(View view) {

        btn_do = view.findViewById(R.id.create_new_group);
        image_settings = view.findViewById(R.id.settings);
        btn_do.setOnClickListener(this);
        image_settings.setOnClickListener(this);
        btn_do.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                ProfileSetupActivity.startActivity(getContext());
                return false;
            }
        });

    }

    private void logout() {
        LogoutPresenter logoutPresenter = new LogoutPresenter(this);
        logoutPresenter.logout();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_new_group:
                SetProfilePicture();
                break;
                case R.id.settings:
                    Log.e(TAG, "logging out");
                    logout();
                    break;

            }

        }


    private void SetProfilePicture() {


        CheckPermissions();

    }

    private void CheckPermissions() {

        String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        String message = "We need permission to access your photos for to insure a  better experience.";
        PermissionRequestActivity.start(getActivity(), REQUEST_READ_AND_WRITE, PERMISSIONS, message, message);

        if (checkPermissionForReadExtertalStorage(getContext()))
            CallGalleryIntent();
    }

    private void CallGalleryIntent() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST);


    }

    private void SetPicture(int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            Uri selectedImage = intent.getData();
            String pathhh = FilePath.getPath(getContext(),selectedImage);

            Log.e(TAG, pathhh + " __________________________________________________");
            String[] filePathColumn = {MediaStore.Images.ImageColumns.DATA};

            assert selectedImage != null;
            Cursor cursor = Objects.requireNonNull(getActivity()).getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            String path_to_save_to = Constants.FULL_PATH_TO_PICTURES;
            Log.e(TAG, "Original Picture dir  -- " + picturePath);
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            Log.e(TAG, "Picture dir to save to -- " + path_to_save_to);
            SavePhoto(bitmap);

            MainActivity.bottomNav.updateImageProfile(picturePath);

        } else {
            Log.e(TAG , "setPicture method result code " + resultCode + " error");
        }

    }

    private void SavePhoto(Bitmap bitmap) {

        SavePhotoToFirebaseDabase(bitmap);

    }

    public void SavePhotoToFirebaseDabase(Bitmap bitmap) {

        mpresenter = new PhotoPresenter(this);
        mpresenter.addPhoto(FirebaseAuth.getInstance().getCurrentUser(), bitmap);


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {

            case REQUEST_READ_AND_WRITE:
                break;
            case GALLERY_REQUEST:
                SetPicture(resultCode, data);
                break;
        }

    }

    @Override
    public void onAddPhotoSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddPhotoFailure(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLogoutSuccess(String message) {
        LoginActivity.startActivity(getContext());
    }

    @Override
    public void onLogoutFailure(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public static boolean checkPermissionForReadExtertalStorage(Context ctz) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int result = ctz.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }


}
