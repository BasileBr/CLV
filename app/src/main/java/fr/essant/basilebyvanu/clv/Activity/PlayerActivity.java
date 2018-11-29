package fr.essant.basilebyvanu.clv.Activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import java.io.IOException;
import java.text.ParseException;
import java.util.ConcurrentModificationException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fr.essant.basilebyvanu.clv.MyAppWebViewClient;
import fr.essant.basilebyvanu.clv.NameViewModel;
import fr.essant.basilebyvanu.clv.Objet.MenuChapitre;
import fr.essant.basilebyvanu.clv.R;


public class PlayerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        test = new MenuChapitre(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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

//                switch (mCurrentPosition){
//                    case (mCurrentPosition<100000) : debut.setBackgroundColor(0xFFFF0000);
//                        break;
//                    case 100000 : chapitre1.setBackgroundColor(0xFFFF0000);
//                        break;
//                    case 200000 : chapitre2.setBackgroundColor(0xFFFF0000);
//                        break;
//                    case 300000 : chapitre3.setBackgroundColor(0xFFFF0000);
//                        break;
//                    case 400000 : fin.setBackgroundColor(0xFFFF0000);
//                        break;
//                }
                if(mCurrentPosition<100000){
                    debut.setBackgroundColor(0xFFFF0000);
                }
                if(mCurrentPosition<200000 && mCurrentPosition>=100000){
                     chapitre1.setBackgroundColor(0xFFFF0000);
                }
                if(mCurrentPosition<300000 && mCurrentPosition>=200000){
                    chapitre2.setBackgroundColor(0xFFFF0000);
                }
                if(mCurrentPosition<400000 && mCurrentPosition>=300000){
                    chapitre3.setBackgroundColor(0xFFFF0000);
                }
                if(mCurrentPosition>=400000){
                    fin.setBackgroundColor(0xFFFF0000);
                }

                //debut.setBackgroundColor(0xFFFF0000);
                //mCurrentPosition=newValue;
                //mNameTextView.setText(newName);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.getmCurrentPosition().observe(this, nameObserver);

        context = this;

        //afficher le texte de chargement de la vidéo
        mBufferingTextView.setVisibility(VideoView.VISIBLE);

        //récupération du lien de la vidéo
        final Uri videoUri = getMedia(VIDEO_SAMPLE);

        //charger la vidéo dans la Video view
        mVideoView.setVideoURI(videoUri);

        // Charge l'url
        mWebView.loadUrl("https://www.google.fr");
        mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

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
                                // mVideoView.seekTo(0);
                                //  mVideoView.start();
                                //mCurrentPosition=0;
                //                mModel.setPosition(mCurrentPosition);
                //                mVideoView.seekTo(mModel.getmCurrentPosition().getValue());
                                mVideoView.seekTo(0);
                                mCurrentPosition = mediaPlayer.getCurrentPosition();
                                mModel.getmCurrentPosition().setValue(mCurrentPosition);
                            }
                        });

                        chapitre1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                // mVideoView.seekTo(120000);
                                // mVideoView.start();
                                //mCurrentPosition=120000;
                //                mModel.setPosition(mCurrentPosition);
                //                mVideoView.seekTo(mModel.getmCurrentPosition().getValue());

                                // mModel.setmCurrentPosition(mCurrentPosition);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Log.d("coucou","basile");
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    private Uri getMedia(String mediaName) {
//        return Uri.parse("android.resource://" + getPackageName() +
//                "/raw/" + mediaName);
//    }

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
                mModel.getmCurrentPosition().observe(context, nameObserver);
            }
        };

        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

    }
}
