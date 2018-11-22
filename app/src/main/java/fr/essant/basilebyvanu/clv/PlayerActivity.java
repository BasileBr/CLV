package fr.essant.basilebyvanu.clv;

import android.net.http.SslError;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.URLUtil;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import android.webkit.WebViewClient;

public class PlayerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WebView mWebView;
    private VideoView mVideoView;
    private MediaController controller;
    private TextView mBufferingTextView;
    private Button debut;
    private Button chapitre1;
    private Button chapitre2;
    private Button chapitre3;
    private Button fin;


    private int mCurrentPosition = 0;
    private static final String PLAYBACK_TIME = "play_time";
    private static final String VIDEO_SAMPLE = "https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
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
        controller.setMediaPlayer(mVideoView);
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

        // Configure la webview pour l'utilisation du javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Permet l'ouverture des fenêtres
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // Autorise le stockage DOM (Document Object Model)
        webSettings.setDomStorageEnabled(true);

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

        //chapitres
        debut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.seekTo(0);
                mVideoView.start();
            }
        });

        chapitre1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.seekTo(120000);
                mVideoView.start();
            }
        });

        chapitre2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.seekTo(360000);
                mVideoView.start();
            }
        });

        chapitre3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.seekTo(480000);
                mVideoView.start();
            }
        });

        fin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mVideoView.seekTo(585000);
                mVideoView.start();
            }
        });

        //seekcomplete

    }

    /**
     * initialiser le player video
     */
    private void initializePlayer() {

        //afficher le texte de chargement de la vidéo
        mBufferingTextView.setVisibility(VideoView.VISIBLE);

        //récupération du lien de la vidéo
        final Uri videoUri = getMedia(VIDEO_SAMPLE);

        //charger la vidéo dans la Video view
        mVideoView.setVideoURI(videoUri);




        //retirer le texte de chargement lorsque la vidéo dépasse 1msec
        mVideoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mBufferingTextView.setVisibility(VideoView.INVISIBLE);

                        controller.setAnchorView(mVideoView);


                        if (mCurrentPosition > 0) {
                            mVideoView.seekTo(mCurrentPosition);
                        } else {
                            mVideoView.seekTo(1);
                        }

                        mVideoView.start();
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
}
