package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;


public class PhrasesFragment extends Fragment {

    public PhrasesFragment() {
        // Required empty public constructor
    }



    private MediaPlayer mediaPlayer;

    private AudioManager mAudioResource;

    public void releaseMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();

            mediaPlayer = null;
            mAudioResource.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }




    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (i == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            } else if (i == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }

        }
    };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioResource = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinne oyaase'ne", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is..", "oyaaset", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "micheksus", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I'm feeling good", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "eenes'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I'm coming", "hee'eenem", R.raw.phrase_yes_im_coming));
        words.add(new Word("I'm coming", "eenem", R.raw.phrase_im_coming));
        words.add(new Word("Let's go", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here", "anni'nem", R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);
                releaseMediaPlayer();
                int result= mAudioResource.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                }}
        });

        return rootView;
    }


}
