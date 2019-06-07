package com.skynet.ateu.medcarry.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skynet.ateu.medcarry.R;

public class SelecionaFoto extends DialogFragment {
    private static final String TAG = "SelecionaFoto";
    private static final int PICK_FILE_REQUEST_CODE = 7612;
    private static final int TAKE_PHOTO_REQUEST_CODE = 122;

    private TextView mSelecionaFoto;
    private TextView mTiraFoto;


    public interface onFotoSelecionadaistener{
        void getImagemPath(Uri path);
        void getImageBitmap(Bitmap bitmap);
    }
    onFotoSelecionadaistener mOnFotoSelecionadaistener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seleciona_foto,container,false);
        mSelecionaFoto = view.findViewById(R.id.tv_selecionaFoto_memoria);
        mTiraFoto = view.findViewById(R.id.tv_selecionaFoto_tirar);
        mSelecionaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"onClick:seleciona foto");
                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_FILE_REQUEST_CODE);
            }
        });
        mTiraFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"onClick:Tira foto");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,TAKE_PHOTO_REQUEST_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.i(TAG,"onActivityResult: image uri :"+ picturePath);
            mOnFotoSelecionadaistener.getImagemPath(selectedImage);
            getDialog().dismiss();
        }else if(requestCode == TAKE_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Bitmap foto =  (Bitmap) data.getExtras().get("data");
            Log.i(TAG,"onActivityResult: foto tirada ");
            mOnFotoSelecionadaistener.getImageBitmap(foto);
            getDialog().dismiss();

        }
    }

    @Override
    public void onAttach(Context context) {
        try {
            mOnFotoSelecionadaistener = (onFotoSelecionadaistener) getTargetFragment();
        }catch (ClassCastException e){
            Log.d(TAG,"onAttach: ClassCastException: "+e.getMessage());
        }
        super.onAttach(context);

    }
}
