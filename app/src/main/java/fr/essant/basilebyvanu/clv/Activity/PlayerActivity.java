package fr.essant.basilebyvanu.clv.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fr.essant.basilebyvanu.clv.MyAppWebViewClient;
import fr.essant.basilebyvanu.clv.Model.NameViewModel;
import fr.essant.basilebyvanu.clv.Objet.MenuChapitre;
import fr.essant.basilebyvanu.clv.R;

/**
 * gilebr1
 */
public class PlayerActivity extends AppCompatActivity{

    private WebView mWebView;
    private VideoView mVideoView;
    private MediaController controller;
    private TextView mBufferingTextView;
    private Button debut;
    private Button chapitre1;
    private Button chapitre2;
    private Button chapitre3;
    private Button fin;
    private NameViewModel mModel;
    private int mCurrentPosition = 0;
    private static final String PLAYBACK_TIME = "play_time";
    private static final String VIDEO_SAMPLE = "https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4";
    public Observer<Integer> nameObserver;

    private MenuChapitre test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        test = new MenuChapitre(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //récupère l'identifiant de l'object textview pour afficher un message d'attente lors du chargement de la vidéo
        mBufferingTextView = findViewById(R.id.buffering_textview);

        debut=findViewById(R.id.debut);
        chapitre1=findViewById(R.id.chapitre1);
        chapitre2=findViewById(R.id.chapitre2);
        chapitre3=findViewById(R.id.chapitre3);
        fin=findViewById(R.id.fin);

        //récupère l'identifiant de la vidéo
        mVideoView = findViewById(R.id.videoview);

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }

        //création du média controler et association du media avec la vidéo
        controller = new MediaController(this);
        //controller.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controller);


        // Déclare mWebView à activity_main (le layout)
        mWebView = (WebView) findViewById(R.id.activity_main_webview);

        // Configure la webview pour l'utihttps://google.com/lisation du javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Permet l'ouverture des fenêtres
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // Autorise le stockage DOM (Document Object Model)
        webSettings.setDomStorageEnabled(true);

        // Get the ViewModel.
        mModel = ViewModelProviders.of(this).get(NameViewModel.class);

        //récupération du lien de la vidéo
        final Uri videoUri = getMedia(VIDEO_SAMPLE);

        //charger la vidéo dans la Video view
        mVideoView.setVideoURI(videoUri);

        // Charge l'url

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        mWebView.loadUrl("https://www.google.fr");
        mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");

       // mModel.
        //seekcomplete
    }

    /**
     * initialiser le player video
     */
    private void initializePlayer() {

        // Create the observer which updates the UI.
        nameObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newValue) {
                // Update the UI, in this case, a TextView.
                //  mCurrentPosition=newValue;
                mCurrentPosition=mModel.getmCurrentPosition().getValue();
                Log.d("Observer",Integer.toString(mCurrentPosition));

                if(mCurrentPosition>200 && mCurrentPosition<100000){
                    debut.setBackgroundColor(0xFFFF0000);
                    chapitre1.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre2.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre3.setBackgroundColor(android.R.drawable.btn_default);
                    fin.setBackgroundColor(android.R.drawable.btn_default);

                    String lien = test.getChapitre(0).getLien().replace("\""," ");
                    mWebView.setWebViewClient(new WebViewClient() {

                        @Override
                        public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                            handler.proceed();
                        }
                    });
                    //mWebView.setWebViewClient(new WebViewClient());
                    mWebView.loadUrl(lien);
                    mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");


                }
                if(mCurrentPosition<200000 && mCurrentPosition>=100000){
                    mWebView.setWebViewClient(new WebViewClient());
                    mWebView.loadUrl(test.getChapitre(10).getLien().replace("\""," "));
//

                    chapitre1.setBackgroundColor(0xFFFF0000);
                    debut.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre2.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre3.setBackgroundColor(android.R.drawable.btn_default);
                    fin.setBackgroundColor(android.R.drawable.btn_default);

                }
                if(mCurrentPosition<300000 && mCurrentPosition>=200000){
                    mWebView.setWebViewClient(new WebViewClient());
                    mWebView.loadUrl(test.getChapitre(20).getLien().replace("\""," "));

                    chapitre2.setBackgroundColor(0xFFFF0000);

                    debut.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre1.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre3.setBackgroundColor(android.R.drawable.btn_default);
                    fin.setBackgroundColor(android.R.drawable.btn_default);
                }
                if(mCurrentPosition<400000 && mCurrentPosition>=300000){
                    mWebView.setWebViewClient(new WebViewClient());
                    mWebView.loadUrl(test.getChapitre(30).getLien().replace("\""," "));

                    chapitre3.setBackgroundColor(0xFFFF0000);

                    debut.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre1.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre2.setBackgroundColor(android.R.drawable.btn_default);
                    fin.setBackgroundColor(android.R.drawable.btn_default);
                }
                if(mCurrentPosition>=400000){
                    mWebView.setWebViewClient(new WebViewClient());
                    mWebView.loadUrl(test.getChapitre(40).getLien().replace("\""," "));

                    fin.setBackgroundColor(0xFFFF0000);

                    debut.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre1.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre2.setBackgroundColor(android.R.drawable.btn_default);
                    chapitre3.setBackgroundColor(android.R.drawable.btn_default);

                }

            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.getmCurrentPosition().observe(this, nameObserver);


        //afficher le texte de chargement de la vidéo
        mBufferingTextView.setVisibility(VideoView.VISIBLE);



        /*
         * Les instructions ci-dessous permettent de forcer l'application
         * à ouvrir les Url directement dans l'application et non dans
         * un navigateur externe. MyAppWebViewClient() est la fonction
         * contenue dans le fichier MyAppWebViewClient.java .
         */

        mWebView.setWebViewClient(new MyAppWebViewClient() {
            @Override
            // Fonction qui permet l'affichage de la page lorsque tout est chargé (événement onPageFinished)

            public void onPageFinished(WebView view, String url) {
                findViewById(R.id.activity_main_webview).setVisibility(View.VISIBLE);
            }
        });
        String[] strings = null;

        main(strings);

        //retirer le texte de chargement lorsque la vidéo dépasse 1msec
        mVideoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(final MediaPlayer mediaPlayer) {

                        mBufferingTextView.setVisibility(VideoView.INVISIBLE);
                        controller.setAnchorView(mVideoView);
                        mVideoView.start();

                        if (mCurrentPosition > 0) {
                            mVideoView.seekTo(mCurrentPosition);
                        } else {
                            mVideoView.seekTo(1);
                        }

                        //chapitres
                        debut.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {

                                //mModel.setmCurrentPosition(mCurrentPosition);

                                mWebView.loadUrl(test.getChapitre(0).getLien().replace("\""," "));
                                mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
                                mWebView.setWebViewClient(new WebViewClient() {

                                    @Override
                                    public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                                        handler.proceed();
                                    }
                                });
                                Log.d("PlayerActicity",test.getChapitre(10).getLien().replace("\""," "));

                                mVideoView.seekTo(0);
                                mCurrentPosition = mediaPlayer.getCurrentPosition();
                                mModel.getmCurrentPosition().setValue(mCurrentPosition);
                            }
                        });

                        chapitre1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {


                                mWebView.loadUrl(test.getChapitre(10).getLien().replace("\""," "));
                                mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
                                mWebView.setWebViewClient(new WebViewClient() {

                                    @Override
                                    public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                                        handler.proceed();
                                    }
                                });
                                Log.d("PlayerActicity",test.getChapitre(10).getLien().replace("\""," "));

                                mVideoView.seekTo(100000);
                                mCurrentPosition = mediaPlayer.getCurrentPosition();
                                mModel.getmCurrentPosition().setValue(mCurrentPosition);
                            }
                        });

                        chapitre2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                //mModel.setmCurrentPosition(mCurrentPosition);

                                mWebView.loadUrl(test.getChapitre(20).getLien().replace("\""," "));
                                mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
                                mWebView.setWebViewClient(new WebViewClient() {

                                    @Override
                                    public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                                        handler.proceed();
                                    }
                                });
                                Log.d("PlayerActicity",test.getChapitre(20).getLien().replace("\""," "));

                                mVideoView.seekTo(200000);
                                mCurrentPosition = mediaPlayer.getCurrentPosition();
                                mModel.getmCurrentPosition().setValue(mCurrentPosition);
                            }
                        });

                        chapitre3.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {

                                mWebView.loadUrl(test.getChapitre(30).getLien().replace("\""," "));
                                mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
                                mWebView.setWebViewClient(new WebViewClient() {

                                    @Override
                                    public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                                        handler.proceed();
                                    }
                                });
                                Log.d("PlayerActicity",test.getChapitre(30).getLien().replace("\""," "));

                                mVideoView.seekTo(300000);
                                // mModel.setmCurrentPosition(mCurrentPosition);
                                mCurrentPosition = mediaPlayer.getCurrentPosition();
                                mModel.getmCurrentPosition().setValue(mCurrentPosition);
                            }
                        });

                        fin.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {

                                mWebView.loadUrl(test.getChapitre(40).getLien().replace("\""," "));
                                mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
                                mWebView.setWebViewClient(new WebViewClient() {

                                    @Override
                                    public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                                        handler.proceed();
                                    }
                                });
                                Log.d("PlayerActicity",test.getChapitre(40).getLien().replace("\""," "));

                                mVideoView.seekTo(400000);
                                // mModel.setmCurrentPosition(mCurrentPosition);
                                mCurrentPosition = mediaPlayer.getCurrentPosition();
                                mModel.getmCurrentPosition().setValue(mCurrentPosition);
                            }
                        });

                        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                            @Override
                            public void onSeekComplete(MediaPlayer mp) {
                                int seek_pos = mediaPlayer.getCurrentPosition();
                                Log.d("Seek",Integer.toString(seek_pos));
                                mModel.getmCurrentPosition().setValue(seek_pos);
                            }
                        });


                    }
                });
    }



    /**
     * Stoper la vidéo et libérer les ressources associées
     */
    private void releasePlayer() {
        mVideoView.stopPlayback();
    }

    @Override
    protected void onStart() {
        super.onStart();

        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();

        releasePlayer();
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        mVideoView.seekTo(mModel.getmCurrentPosition().getValue());
    }*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current playback position (in milliseconds) to the
        // instance state bundle.
        outState.putInt(PLAYBACK_TIME, mVideoView.getCurrentPosition());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player, menu);
        return true;
    }



    private Uri getMedia(String mediaName) {
        if (URLUtil.isValidUrl(mediaName)) {
            // media name is an external URL
            return Uri.parse(mediaName);
        } else { // media name is a raw resource embedded in the app
            return Uri.parse("android.resource://" + getPackageName() +
                    "/raw/" + mediaName);
        }
    }

    public void main(String[] args) {
        final Runnable task = new Runnable() {

            @Override
            public void run() {

                mCurrentPosition = mVideoView.getCurrentPosition();
                System.out.println("exécuté toutes les secondes" + String.valueOf(mCurrentPosition));
                mModel.getmCurrentPosition().postValue(mCurrentPosition);


            }
        };

        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

    }
}
