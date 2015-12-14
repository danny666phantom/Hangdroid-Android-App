package edu.slcc.daniellorenzo.colors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameMultiActivity extends Activity {

    String theWord = "";
    int badLetterCount = 0;
    int goodLetterCount = 0;
    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);

        // Get the word from the intent.
        String wordToGuess = "";
        wordToGuess = getIntent().getStringExtra("GUESS_WORD"); // Defaults if data does not cr

        Log.d("MYLOG", "Word Sent: " + wordToGuess);

        createTextViews(wordToGuess);

        theWord = wordToGuess;

        //setWord();

    }

    private void createTextViews(String word) {
        // Get the layout .
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        // 1 - Create the textview and all of its properties in java code.
        // 2 - Create one textview in xml and copy it to the others. (single_letter.xml)
        for (int i = 0; i < word.length(); i++){
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.single_letter, null);
            // Add view to layout container;
            layout.addView(textView);
            // Test run and look for text views.

        }

    }


    // Called when user clicks on button set in activity_game.xml
    public void newLetter(View view) {


        // Find the text box with a letter in it using its id... then cast it.
        EditText editText = (EditText) findViewById(R.id.editTextLetter);

        // Set it to my String variable.
        String letter = editText.getText().toString();

        // Blank out text field for next guess.
        editText.setText("");

        // Test I am getting the letter.
        Log.d("MYLOG", "The letter is: " + letter);

        // Check the length of the letter.
        if (letter.length() == 1) {
            checkLetter(letter);
        } else {                // (context, text, duration);
            Toast.makeText(this, "Please Enter a Single Letter", Toast.LENGTH_SHORT).show();
        }

    }

    // Recieves the users letter guess and checks if the letter given exist in.
    public void checkLetter(String letter) {

        boolean letterGuessed = false;

        Toast.makeText(this, "checkLetter: " + letter, Toast.LENGTH_SHORT).show();

        // Pharse to char for comparison.
        char aLetter = letter.charAt(0);

        // Loop to look for the new letter.
        for (int i = 0; i < theWord.length(); i++) {
            if (theWord.charAt(i) == aLetter) {
                Log.d("MYLOG", "Letter Found " + aLetter);
                letterGuessed = true;
                goodLetterCount++;
                showLetter(i, aLetter);
            }
        }   // End of for loop.

        // Letter was not found in word. Add it to the list of wrong letters.
        if (!letterGuessed)
        {
            Log.d("MYLOG", "Letter NOT Found " + aLetter);
            // Count bad letters
            badLetterCount++;
            // Send the failed letter as a string
            wrongLetter(Character.toString(aLetter));
        }

        letterGuessed = false;

        // Won! If the guess count is equal to word length.
        Log.d("MYLOG", "Check for win: " + goodLetterCount + " " + theWord.length());

        if (goodLetterCount == theWord.length()){

            Toast.makeText(this, "You've Won!", Toast.LENGTH_LONG).show();
            points++;
            gameOver();

        }


    }   // End of checkLetter.

    // WRONG LETTER METHOD.
    // Called when users guessed letter is not found in the word.
    public void wrongLetter(String badLetter){

        // Make a reference of the textview of wrong guessed letters in the activity
        TextView textView = (TextView) findViewById(R.id.textViewWrong);

        // Save previous letters
        String previousLetters = textView.getText().toString();

        // Add or append.
        Log.d("MYLOG", "Bad Letter: " + badLetter + " " + badLetterCount);

        if (badLetterCount > 1) {
            textView.setText(previousLetters + " " + badLetter);
        } else {
            textView.setText(badLetter);
        }

        // Make a reference of the IMAGEVIEW in activity_game.xml using the R reference.
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        //...
        if (badLetterCount == 1)
            imageView.setImageResource(R.drawable.hangdroid_1);
        if (badLetterCount == 2)
            imageView.setImageResource(R.drawable.hangdroid_2);
        if (badLetterCount == 3)
            imageView.setImageResource(R.drawable.hangdroid_3);
        if (badLetterCount == 4)
            imageView.setImageResource(R.drawable.hangdroid_4);
        if (badLetterCount == 5)
            imageView.setImageResource(R.drawable.hangdroid_5);
        if (badLetterCount > 5){    // User lost!
            Toast.makeText(this, "Apple Rules!", Toast.LENGTH_LONG).show();
            gameOver();

        }
    }


    // Called when letter was found in word.
    // Position: position the letter was found.
    // letterGuessed: users guessed letter.
    // Change the display layout with the new letter.
    public void showLetter(int position, char letterGuessed) {


        // Make a referrence of the LinearLayout in the activity_game XML using the R reference.
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        // Make a reference of the textview of the child within the layout that matches the position of the guessed letter.
        TextView textView = (TextView) layout.getChildAt(position);

        // Replace the "_" of the text view of the guessed letter to the textview.
        textView.setText(Character.toString(letterGuessed));

    }
    public void gameOver(){
        // Create intent.
        Intent intent = new Intent(this, GameOverActivity.class);
        // Send data with intent.
        intent.putExtra("PointsID", points);
        // Start activity.
        startActivity(intent);
        // Clear for new game.
        clearScreen();
        // Video new word.
        setWord();

    } // End of gameOver.

    private void setWord() {
        String words = "aahs abet able ably abut aced aces ache acid acme acne acre acts adds adze afar afro agar aged ages agog ague ahas ahem ahoy aide aids ails aims airs airy ajar akin alas albs alef ales alga ally alms aloe alps also alto alum ambo amen amid amok amps anal ands anew anon ante ants anus aped aper apes apex apps aqua arch arcs area ares aria arid arks arms army arts arty ashs ashy asks asps atom atop aunt aura auto aver avid avow away awed awes awls awns awny awol awry axed axel axes axis axle axon ayes ayin baas babe baby back bade bags baht bail bait bake bald bale balk ball balm band bane bang bank bans barb bard bare barf bark barm barn bars base bash bask bass bath bats baud bawd bawl bays bead beak beam bean bear beat beau beds beef been beep beer bees beet begs bell belt bend bent berk berm best beta beth bets bevy bias bibs bide bids bike bile bilk bill bind bins bios bird birr bite bits bitt blab blah blat bleb bled blew blip blob blog blot blow blue blur boar boas boat bobs bode body bogs boil bold boll bolt bomb bond bone bonk bony boob book boom boon boor boos boot bops bore born boss both bots bout bowl bows boxy boys brad brag bran bras brat bray bred brew brie brim bris brow bubo buck buds buff bugs bulb bulk bull bump bums bunk buns bunt buoy burl burn burp burr burs bury bush busk bust busy buts butt buys buzz byes byte cabs cads cafe cage cake calf call calm calx came camp cams cane cans cant cape caps card care carp cars cart case cash cask cast cats cave caws ceca cede cedi cees cell celt cent chad chap char chat chef chew chic chin chip chis chop chow chub chug chum cite city clad clan clap claw clay clef clip clod clog clot club clue coal coat coax cobs cock coda code cods coed cogs coho coif coil coin cola cold cole colt coma comb come cone conk cons cook cool coop coos coot cope cops copy cord core cork corm corn cost cosy cots coup cove cowl cows coys cozy crab crag cram crap crew crib crop crow crud crux cube cubs cuds cued cues cuff cull cult cups curb curd cure curl curs curt cusp cuss cute cuts cwms cyan cyst czar dabs dado dads daft dame damn damp dams dank dare dark darn dart dash data date daub dawn days daze dead deaf deal dean dear debt deck deed deem deep deer dees deft defy deil dele delf deli dell deme demo demy dene dens dent deny dere derm desk deva dews dewy deys dhow dial dibs dice died dies diet digs dill dime dims dine ding dins dips dire dirt disc dish disk ditz diva dive dock dodo doer does doff doge dogs dole doll dolt dome done dons doom door dope dork dorm dose dote doth dots dove down doze dozy drab drag dram draw dreg drew drip drop drug drum dual dubs duck duct dude duds duel dues duet duff duke dull duly dumb dump dune dung dunk duos dupe dusk dust duty dyed dyer dyes dyne each earl earn ears ease east easy eats eave ebbs echo ecru eddy edge edgy edit eeks eels eely eery effs eggs egos eked eker ekes elks ells elms else emir emit emus ends enol envy eons epic eras ergo eros etas etch euro even ever eves evil ewer ewes exam exes exit exon expo eyed eyes face fact fade fads fail fain fair fake fall fame fang fans fare farm fast fate fats faun faux fave fawn faze fear feat feds feed feel fees feet fell felt fend fens fern feta feud fibs figs file fill film find fine fink fins fire firm firs fish fist fits five fizz flab flag flan flap flat flaw flax flay flea fled flee flew flex flip flit floe flog flop flow flub flue flux foal foam fobs foci foes fogs fogy foil fold folk fond font food fool foot fora fore fork form fort foul four fowl foxy fray free fret friz frog from fuel full fume fumy fund funk furs fury fuse fuss fuzz gabs gaff gaga gage gags gain gait gala gale gall gals game gang gape gaps garb gash gasp gate gave gawk gaze gear geek gees geld gell gels gems gene gent germ gets gift gigs gild gill gilt gimp gins gird girl gist give glad glee glen glia glib glob glow glue glum glut gnat gnaw gnus goad goal goat gobs gods goer goes goji gold golf gone gong good goof goon goop goos gore gory gosh goth gout gown grab gram gray grew grey grid grim grin grip grit grow grub guck guff gulf gull gulp gums gunk guns guru gush gust guts guys gyms gyps gyre gyro hack hags hail hair half hall halo halt hams hand hang haps hard hare hark harm harp hash hasp hate hath hats haul have hawk haws hays haze hazy head heal heap hear heat heck heed heel heir held hell helm help heme hems hens herb herd here hero hers heth hewn hews hick hide high hike hill hilt hind hint hips hire hiss hits hive hoar hoax hobo hoed hoer hoes hogs hold hole holy home hone honk hood hoof hook hoop hoot hope hops horn hose host hots hour howl hows hubs hued hues huff huge hugs huhs hula hulk hull hump hums hung hunk hunt hurl hurt hush husk huts hymn hype hypo ibex ibis iced icer ices icky icon idea ides idle idly idol iffy ilea ills imam imps inch inks inky inns into ions iota ired ires iris irks iron isle itch item jabs jack jade jags jail jamb jams jars java jaws jays jazz jean jeep jeer jell jerk jest jets jibe jigs jilt jink jinx jive jobs jock jogs join joke jolt josh jots jowl joys judo jugs juke july jump june junk jury just jute juts kale kaph kays keek keel keen keep kegs kelp kept kern keys kick kids kill kiln kilt kina kind kine king kink kips kiss kite kits kiwi knee knew knit knob knot know kook kudu kuna kyak kyat labs lace lack lacy lade lads lady lags laid lain lair lake lamb lame lamp land lane lank laps lard lari lark lash lass last late laud lava lave lawn laws lays laze lazy lead leaf leak lean leap lear leas leek leer left legs leks lend lens lent less lest lets leus levs levy lewd liar lice lick lids lied lien lier lies lieu life lift like lily limb lime limn limo limp limy line link lint lion lips lira lire lisp list lite live load loaf loam loan lobe lobs loch loci lock loco lode loft logo logs loin loll lone long look loom loon loop loos loot lope lops lord lore lose loss lost loti lots loud lout love lows luau lube luck luff luge lugs lull lump lung lure lurk lush lust lute lynx lyre mace mach made mage magi maid mail maim main make male mall malt mama mane mans many maps mara mare mark marl mars mart mash mask mass mast mate math mats matt maul maws mayo mays maze mead meal mean meat meek meet meld melt memo mend mens menu meow mere mesa mesh mess mews mica mice midi miff mild mile milk mill mils mime mind mine mini mink mint minx mire miss mist mite mitt moan moat mobs mock mode mods moho mold mole molt moms monk mood moon moor moos moot mope mops more moss most moth move mown mows much muck muff mugs mule mull mums muon murk muse mush musk must mute mutt myna myth nabs nags nail name nape naps naut nave navy nays nazi neap near neat neck need neon nerd nest nets nevi news newt next nibs nice nick nigh nils nine nips nits nobs node nods noel none noon nope norm nose nosy note noun nova nude nuke null numb nuns nuts oafs oaks oars oath oats obey oboe odds odes odor offs ogle ogre ohms oils oily oink okay okra oleo omen omit omni once ones only onto onus onyx oohs ooid oops ooze oozy opal open opts oral orbs orca ores oryx ouch ours oust outs ouzo oval oven over ovum owed ower owes owls owly owns oxen oxes pace pack pact pads page paid pail pain pair pale pall palm pals pane pang pans pant papa pare park pars part pass past pate path pats pave pawn paws pays peak peal pear peas peat peck peek peel peep peer pegs pelf pelt pend pens pent peon peps perk perm pert peso pest pets pews phis pick pied pier pies pigs pike pile pili pill pimp pine ping pink pins pint pipe pips pita pith pits pity pius plan play plea pled plod plop plot plow ploy plug plum plus pock pods poem poet pogo poke poky pole poll polo pomp pond pony pooh pool poop poor pope pops pore pork porn port pose posh post posy pots pouf pour pout poxy pram pray prep prey prim prod prom prop pros prow psis pubs puce puck puff pugs puke pull pulp puma pump punk puns punt puny pupa pups pure purr push puts putt pyre qoph quad quay quid quip quit quiz race rack racy raft rage rags raid rail rain rake ramp rams rand rang rank rant rape raps rapt rare rash rasp rate rats rave raws rays raze razz read reak real ream reap rear redo reds reed reef reek reel refs rein rely rend rent repo resh rest revs rhos rial ribs rice rich rick ride rids riel rife riff rift rigs rile rill rily rime rims rind ring rink riot ripe rips rise risk rite rive road roam roan roar robe robs rock rode rods roes roil role roll romp rood roof rook room root rope ropy rose rosy rote rots roue rout rove rows rubs ruby rude rued rues ruff rugs ruin rule rums rune rung runs runt ruse rush rust ruts sack sacs safe saga sage sags sagy said sail sake saki sale salt same sand sane sang sank saps sari sash sass sate save sawn saws says scab scam scan scar scat scot scud scum seal seam sear seas seat sect seed seek seem seen seep seer sees self sell send sent sera sere serf seta sets sewn sews sext sexy shah sham shed shim shin ship shmo shoe shoo shop shot show shun shut sick side sift sigh sign sikh silk sill silo silt sine sing sink sins sips sire sirs site sits sitz size skew skid skim skin skip skis skit slab slam slap slat slaw slay sled slew slid slim slip slit slob sloe slop slot slow slug slum slur smit smog smug smut snag snap snip snit snob snog snot snow snub snug soak soap soar sobs sock soda sods sofa soft soil sold sole solo some soms song sons soon soot sops sore sort sots soul soup sour sown sows soya soys spam span spar spas spat spay sped spin spit spot spry spud spun spur stab stag star stat stay stem step stew stir stop stow stub stud stun stye styx subs such suck suds sued suer sues suet suit sulk sumo sump sums sung sunk suns sure surf swab swag swam swan swap swat sway swig swim swum sync tabs tack taco tact tags tail taka take tala talc tale talk tall tame tamp tams tank tans tape taps tare tarn taro tarp tars tart task taus taut taxa taxi teak teal team tear teas tech teed teem teen tees tell tend tens tent term tern test teth text than that thaw thee them then they thin this thou thru thud thug thus tick tics tide tidy tied tier ties tiff tike tile till tilt time tine ting tins tint tiny tips tire toad toed toes tofu toga toil told toll tomb tome tone tong tons took tool toot tops tore torn toro tort toss tote tots tour tout town tows toys tram trap tray tree trek trim trio trip trod trot troy true tsar tuba tube tubs tuck tufa tuff tuft tugs tums tuna tune turf turn tusk tutu twig twin twit twos tyke type typo tyro tzar ughs ugly ukes ulna umps undo unit unix unto upon urea urge uric urns used user uses uvea vain vale vamp vane vang vans vary vase vast vats vatu veal vear veer vees veil vein vend vent verb very vest veto vets vial vibe vice vied vies view vile vine visa vise void vole volt vote vows vugs wack wade wads waft wage wags waif wail wait wake walk wall wand wane want ward ware warm warn warp wars wart wary wash wasp watt wave wavy waxy ways weak wean wear webs weds weed week ween weep weir weld well welt wend went wept were west wets wham what when whet whew whey whim whip whir whiz whoa whom whop wick wide wife wifi wigs wild wile will wilt wily wimp wind wine wing wink wins wipe wire wiry wise wish wisp wist with wits wive woad woes woke woks wolf womb wons wont wood woof wool woos word wore work worm worn wort wove wows wrap wren writ wyes xray xyst yack yaff yagi yaks yald yams yang yank yaps yard yare yarn yaud yaup yawl yawn yawp yaws yeah yean year yeas yegg yeld yelk yell yelp yens yerk yeti yett yeuk yews yill yins yipe yips yird yirr yodh yods yoga yogh yogi yoke yolk yond yoni yore your yowe yowl yows yoyo yuan yuck yuga yuks yule yurt yutz ywis zags zany zaps zarf zati zeal zebu zeds zees zein zens zero zest zeta zigs zinc zine zing zips ziti zits zoea zoic zone zonk zoom zoon zoos zori zulu zyme";
        String [] wordsArray = words.split(" ");
        Log.d("MYLOG", "Number of words: " + wordsArray.length);

        // Get random number and multiply by the number of words in the array.
        int theNumber = (int) (Math.random() * wordsArray.length);
        Log.d("MYLOG", "Random Number: " + theNumber);

        theWord = wordsArray[theNumber];
        Log.d("MYLOG", "The word is " + theWord);


    } // End of setWord.


    public void clearScreen(){
        // Clear guessed letters.
        TextView textView = (TextView) findViewById(R.id.textViewWrong);
        textView.setText("Guessed Letters");

        // Reset counter.
        badLetterCount = 0;
        goodLetterCount = 0;

        // Clear guessed word layout.
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        // Reset textview to "_".
        for(int i=0; i<layout.getChildCount(); i++){

            TextView childTextView = (TextView) layout.getChildAt(i);
            childTextView.setText("_");

        }
        // Set image back to starting / zero image.
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        if (id == R.id.action_favorite) {
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


} // End of Class.
