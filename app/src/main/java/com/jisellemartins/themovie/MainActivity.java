package com.jisellemartins.themovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jisellemartins.themovie.api.MovieService;
import com.jisellemartins.themovie.model.ResponseMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    Button btnDados;
    TextView txtDados;
    Retrofit retrofit;
    ImageView imgDados;
   ResponseMovie resultsList = new ResponseMovie();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnDados = findViewById(R.id.btnDados);
        txtDados = findViewById(R.id.txtDados);
        imgDados = findViewById(R.id.imgDados);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        final String json = "{\"results\":[{\"popularity\":244.143,\"vote_count\":1871,\"video\":false,\"poster_path\":\"\\/iZf0KyrE25z1sage4SYFLCCrMi9.jpg\",\"id\":530915,\"adult\":false,\"backdrop_path\":\"\\/2WgieNR1tGHlpJUsolbVzbUbE1O.jpg\",\"original_language\":\"en\",\"original_title\":\"1917\",\"genre_ids\":[18,36,10752],\"title\":\"1917\",\"vote_average\":8.1,\"overview\":\"At the height of the First World War, two young British soldiers, Schofield and Blake are given a seemingly impossible mission. In a race against time, they must cross enemy territory and deliver a message that will stop a deadly attack on hundreds of soldiers—Blake's own brother among them.\",\"release_date\":\"2019-12-10\"},{\"popularity\":195.04,\"vote_count\":550,\"video\":false,\"poster_path\":\"\\/y95lQLnuNKdPAzw9F9Ab8kJ80c3.jpg\",\"id\":38700,\"adult\":false,\"backdrop_path\":\"\\/upUy2QhMZEmtypPW3PdieKLAHxh.jpg\",\"original_language\":\"en\",\"original_title\":\"Bad Boys for Life\",\"genre_ids\":[28,80,53],\"title\":\"Bad Boys for Life\",\"vote_average\":6.5,\"overview\":\"Marcus and Mike are forced to confront new threats, career changes, and midlife crises as they join the newly created elite team AMMO of the Miami police department to take down the ruthless Armando Armas, the vicious leader of a Miami drug cartel.\",\"release_date\":\"2020-01-15\"},{\"popularity\":190.569,\"vote_count\":1196,\"video\":false,\"poster_path\":\"\\/6ApDtO7xaWAfPqfi2IARXIzj8QS.jpg\",\"id\":359724,\"adult\":false,\"backdrop_path\":\"\\/n3UanIvmnBlH531pykuzNs4LbH6.jpg\",\"original_language\":\"en\",\"original_title\":\"Ford v Ferrari\",\"genre_ids\":[28,18],\"title\":\"Ford v Ferrari\",\"vote_average\":7.8,\"overview\":\"American car designer Carroll Shelby and the British-born driver Ken Miles work together to battle corporate interference, the laws of physics, and their own personal demons to build a revolutionary race car for Ford Motor Company and take on the dominating race cars of Enzo Ferrari at the 24 Hours of Le Mans in France in 1966.\",\"release_date\":\"2019-11-13\"},{\"popularity\":101.07,\"vote_count\":3066,\"video\":false,\"poster_path\":\"\\/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg\",\"id\":496243,\"adult\":false,\"backdrop_path\":\"\\/TU9NIjwzjoKPwQHoHshkFcQUCG.jpg\",\"original_language\":\"ko\",\"original_title\":\"기생충\",\"genre_ids\":[35,18,53],\"title\":\"Parasite\",\"vote_average\":8.6,\"overview\":\"All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.\",\"release_date\":\"2019-05-30\"},{\"popularity\":114.461,\"vote_count\":226,\"video\":false,\"poster_path\":\"\\/yJdeWaVXa2se9agI6B4mQunVYkB.jpg\",\"id\":449924,\"adult\":false,\"backdrop_path\":\"\\/ekP6EVxL81lZ4ivcqPsoZ72rY0h.jpg\",\"original_language\":\"cn\",\"original_title\":\"葉問4\",\"genre_ids\":[28,18,36],\"title\":\"Ip Man 4: The Finale\",\"vote_average\":6,\"overview\":\"Following the death of his wife, Ip Man travels to San Francisco to ease tensions between the local kung fu masters and his star student, Bruce Lee, while searching for a better future for his son.\",\"release_date\":\"2019-12-20\"},{\"popularity\":86.574,\"vote_count\":91,\"video\":false,\"poster_path\":\"\\/MBiKqTsouYqAACLYNDadsjhhC0.jpg\",\"id\":486589,\"adult\":false,\"backdrop_path\":\"\\/bga3i5jcejBekr2FCGJga1fYCh.jpg\",\"original_language\":\"en\",\"original_title\":\"Red Shoes and the Seven Dwarfs\",\"genre_ids\":[16,10749],\"title\":\"Red Shoes and the Seven Dwarfs\",\"vote_average\":6.4,\"overview\":\"Princes who have been turned into Dwarfs seek the red shoes of a lady in order to break the spell, although it will not be easy.\",\"release_date\":\"2019-07-25\"},{\"popularity\":85.619,\"vote_count\":2795,\"video\":false,\"poster_path\":\"\\/db32LaOibwEliAmSL2jjDF6oDdj.jpg\",\"id\":181812,\"adult\":false,\"backdrop_path\":\"\\/jOzrELAzFxtMx2I4uDGHOotdfsS.jpg\",\"original_language\":\"en\",\"original_title\":\"Star Wars: The Rise of Skywalker\",\"genre_ids\":[28,12,878],\"title\":\"Star Wars: The Rise of Skywalker\",\"vote_average\":6.5,\"overview\":\"The surviving Resistance faces the First Order once again as the journey of Rey, Finn and Poe Dameron continues. With the power and knowledge of generations behind them, the final battle begins.\",\"release_date\":\"2019-12-18\"},{\"popularity\":90.64,\"vote_count\":1172,\"video\":false,\"poster_path\":\"\\/7GsM4mtM0worCtIVeiQt28HieeN.jpg\",\"id\":515001,\"adult\":false,\"backdrop_path\":\"\\/agoBZfL1q5G79SD0npArSlJn8BH.jpg\",\"original_language\":\"en\",\"original_title\":\"Jojo Rabbit\",\"genre_ids\":[35,18,10752],\"title\":\"Jojo Rabbit\",\"vote_average\":8.2,\"overview\":\"A World War II satire that follows a lonely German boy whose world view is turned upside down when he discovers his single mother is hiding a young Jewish girl in their attic. Aided only by his idiotic imaginary friend, Adolf Hitler, Jojo must confront his blind nationalism.\",\"release_date\":\"2019-10-18\"},{\"popularity\":67.615,\"vote_count\":69,\"video\":false,\"poster_path\":\"\\/lRZ1U7LeRbW7swneo4BOqNCeKXy.jpg\",\"id\":653567,\"adult\":false,\"backdrop_path\":\"\\/A7AjlQTR1UlaNSYwwPRQ5gQTHyf.jpg\",\"original_language\":\"en\",\"original_title\":\"Miss Americana\",\"genre_ids\":[99],\"title\":\"Miss Americana\",\"vote_average\":8,\"overview\":\"A raw and emotionally revealing look at one of the most iconic artists of our time during a transformational period in her life as she learns to embrace her role not only as a songwriter and performer, but as a woman harnessing the full power of her voice.\",\"release_date\":\"2020-01-31\"},{\"popularity\":71.146,\"vote_count\":2091,\"video\":false,\"poster_path\":\"\\/pjeMs3yqRmFL3giJy4PMXWZTTPa.jpg\",\"id\":330457,\"adult\":false,\"backdrop_path\":\"\\/xJWPZIYOEFIjZpBL7SVBGnzRYXp.jpg\",\"original_language\":\"en\",\"original_title\":\"Frozen II\",\"genre_ids\":[12,16,10402,10751],\"title\":\"Frozen II\",\"vote_average\":7,\"overview\":\"Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.\",\"release_date\":\"2019-11-20\"},{\"popularity\":75.149,\"vote_count\":1686,\"video\":false,\"poster_path\":\"\\/vloNTScJ3w7jwNwtNGoG8DbTThv.jpg\",\"id\":420809,\"adult\":false,\"backdrop_path\":\"\\/skvI4rYFrKXS73BJxWGH54Omlvv.jpg\",\"original_language\":\"en\",\"original_title\":\"Maleficent: Mistress of Evil\",\"genre_ids\":[12,14,10751],\"title\":\"Maleficent: Mistress of Evil\",\"vote_average\":7.2,\"overview\":\"Maleficent and her goddaughter Aurora begin to question the complex family ties that bind them as they are pulled in different directions by impending nuptials, unexpected allies, and dark new forces at play.\",\"release_date\":\"2019-10-16\"},{\"popularity\":71.62,\"vote_count\":1382,\"video\":false,\"poster_path\":\"\\/jyw8VKYEiM1UDzPB7NsisUgBeJ8.jpg\",\"id\":512200,\"adult\":false,\"backdrop_path\":\"\\/zTxHf9iIOCqRbxvl8W5QYKrsMLq.jpg\",\"original_language\":\"en\",\"original_title\":\"Jumanji: The Next Level\",\"genre_ids\":[28,12,35,14],\"title\":\"Jumanji: The Next Level\",\"vote_average\":6.7,\"overview\":\"As the gang return to Jumanji to rescue one of their own, they discover that nothing is as they expect. The players will have to brave parts unknown and unexplored in order to escape the world’s most dangerous game.\",\"release_date\":\"2019-12-04\"},{\"popularity\":60.348,\"vote_count\":1166,\"video\":false,\"poster_path\":\"\\/kTQ3J8oTTKofAVLYnds2cHUz9KO.jpg\",\"id\":522938,\"adult\":false,\"backdrop_path\":\"\\/hl9wGMntYNl4dEYtjgU600GZ8JH.jpg\",\"original_language\":\"en\",\"original_title\":\"Rambo: Last Blood\",\"genre_ids\":[28,18,53],\"title\":\"Rambo: Last Blood\",\"vote_average\":5.9,\"overview\":\"After fighting his demons for decades, John Rambo now lives in peace on his family ranch in Arizona, but his rest is interrupted when Gabriela, the granddaughter of his housekeeper María, disappears after crossing the border into Mexico to meet her biological father. Rambo, who has become a true father figure for Gabriela over the years, undertakes a desperate and dangerous journey to find her.\",\"release_date\":\"2019-09-19\"},{\"popularity\":68.733,\"vote_count\":893,\"video\":false,\"poster_path\":\"\\/wE5aZnk43mWV3BKh7Cv3vODTXro.jpg\",\"id\":331482,\"adult\":false,\"backdrop_path\":\"\\/3uTxPIdVEXxHpsHOHdJC24QebBV.jpg\",\"original_language\":\"en\",\"original_title\":\"Little Women\",\"genre_ids\":[18,10749],\"title\":\"Little Women\",\"vote_average\":8,\"overview\":\"Four sisters come of age in America in the aftermath of the Civil War.\",\"release_date\":\"2019-12-25\"},{\"popularity\":74.917,\"vote_count\":13,\"video\":false,\"poster_path\":\"\\/Z9TfGjM3JSFx1sytqz0RAxzJ5H.jpg\",\"id\":503917,\"adult\":false,\"backdrop_path\":\"\\/1wv5gjKrxaQKn6CyyMYcANY9soM.jpg\",\"original_language\":\"cn\",\"original_title\":\"肥龍過江\",\"genre_ids\":[28,35],\"title\":\"Enter The Fat Dragon\",\"vote_average\":3,\"overview\":\"Fallon Zhu (Donnie Yen) is a Hong Kong police officer – an agile fighter with a high crime detection rate. Dumped by his fiancée Chloe (Niki Chow), Fallon was heartbroken and start indulging in food. In six months, he became 200+ pounds. His superior, Superintendent Huang, puts him in charge of escorting a convict to Japan. A series of mishaps befall him in Japan; not only did he lose all of his belongings, he also lost the convict in his custody. His luck overturned when he met Thor (Wong Jing), another Hong Kong citizen. The both of them join forces to solve the mystery behind Fallon’s convict’s strange and sudden death. Troubles continue to snowball, affecting those around Fallon. Fallon is thus determined to uproot the gang, regardless.\",\"release_date\":\"2020-01-23\"},{\"popularity\":66.133,\"vote_count\":160,\"video\":false,\"poster_path\":\"\\/xxLdm3J8WiPLePIEa1ZfmLcMETT.jpg\",\"id\":448119,\"adult\":false,\"backdrop_path\":\"\\/lG802rseTZcN9mtLsQPVfApEVzM.jpg\",\"original_language\":\"en\",\"original_title\":\"Dolittle\",\"genre_ids\":[28,12,35,14,10751],\"title\":\"Dolittle\",\"vote_average\":6.4,\"overview\":\"After losing his wife seven years earlier, the eccentric Dr. John Dolittle, famed doctor and veterinarian of Queen Victoria’s England, hermits himself away behind the high walls of Dolittle Manor with only his menagerie of exotic animals for company. But when the young queen falls gravely ill, a reluctant Dolittle is forced to set sail on an epic adventure to a mythical island in search of a cure, regaining his wit and courage as he crosses old adversaries and discovers wondrous creatures.\",\"release_date\":\"2020-01-01\"},{\"popularity\":96.289,\"vote_count\":496,\"video\":false,\"poster_path\":\"\\/7gOozJufKJ9WjcIs38KEs08Iq3D.jpg\",\"id\":473033,\"adult\":false,\"backdrop_path\":\"\\/uzvT6tYrU5SxfHe1ieimIGAqyFm.jpg\",\"original_language\":\"en\",\"original_title\":\"Uncut Gems\",\"genre_ids\":[80,18,53],\"title\":\"Uncut Gems\",\"vote_average\":7.5,\"overview\":\"A charismatic New York City jeweler always on the lookout for the next big score makes a series of high-stakes bets that could lead to the windfall of a lifetime. Howard must perform a precarious high-wire act, balancing business, family, and encroaching adversaries on all sides in his relentless pursuit of the ultimate win.\",\"release_date\":\"2019-11-14\"},{\"popularity\":47.562,\"vote_count\":453,\"video\":false,\"poster_path\":\"\\/kDEjffiKgjuGo2DRzsqfjvW0CQh.jpg\",\"id\":549053,\"adult\":false,\"backdrop_path\":\"\\/eZ9wYTk9Gy2zYEv8rhRG3IoPuXG.jpg\",\"original_language\":\"en\",\"original_title\":\"Last Christmas\",\"genre_ids\":[35,18,10749],\"title\":\"Last Christmas\",\"vote_average\":7,\"overview\":\"Kate is a young woman has a habit of making bad decisions, and her last date with disaster occurs after she accepts work as Santa's elf for a department store. However, after she meets Tom there, her life takes a new turn.\",\"release_date\":\"2019-11-07\"},{\"popularity\":54.595,\"vote_count\":258,\"video\":false,\"poster_path\":\"\\/qCDPKUMX5xrxxQY8XhGVCKO3fks.jpg\",\"id\":599975,\"adult\":false,\"backdrop_path\":\"\\/zETkzgle7c6wAeW11snnVUBp67S.jpg\",\"original_language\":\"en\",\"original_title\":\"Countdown\",\"genre_ids\":[27,53],\"title\":\"Countdown\",\"vote_average\":6.3,\"overview\":\"A young nurse downloads an app that tells her she only has three days to live. With time ticking away and a mysterious figure haunting her, she must find a way to save her life before time runs out.\",\"release_date\":\"2019-10-24\"},{\"popularity\":59.212,\"vote_count\":20,\"video\":false,\"poster_path\":\"\\/mBBBXseq4k4dI63k06XIrsc02j8.jpg\",\"id\":542224,\"adult\":false,\"backdrop_path\":\"\\/en1XAePgWqNSXb82luUMmo5u3cF.jpg\",\"original_language\":\"en\",\"original_title\":\"Gretel & Hansel\",\"genre_ids\":[14,27,53],\"title\":\"Gretel & Hansel\",\"vote_average\":5.7,\"overview\":\"A long time ago in a distant fairy tale countryside, a young girl leads her little brother into a dark wood in desperate search of food and work, only to stumble upon a nexus of terrifying evil.\",\"release_date\":\"2020-01-30\"}],\"page\":1,\"total_results\":1079,\"dates\":{\"maximum\":\"2020-02-02\",\"minimum\":\"2019-12-16\"},\"total_pages\":54}";
//        resultsList = new Gson().fromJson(json,ResponseMovie.class);
        btnDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDados();
               /* try {
                    resultsList = new Gson().fromJson(json,ResponseMovie.class);
                }catch (Exception e){
                    Log.e("ERRO",e.getMessage());
                }*/


            }
        });

    }

    private void recuperarDados() {
        MovieService movieService = retrofit.create(MovieService.class);
        Call<ResponseMovie> call = movieService.recuperaDados("aplication/json");

        call.enqueue(new Callback<ResponseMovie>() {
                         @Override
                         public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                             resultsList = response.body();
                         }

                         @Override
                         public void onFailure(Call<ResponseMovie> call, Throwable t) {
                             t.getMessage();
                         }
                     }
        );
    }
}
