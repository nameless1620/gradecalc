package com.nameless1620.gradecalc.ui.views.resources;

import com.nameless1620.gradecalc.ui.MainLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;


@PageTitle("GradeCalc | Resources")
@Route(value = "resources", layout = MainLayout.class)
public class ResourcesView extends VerticalLayout {

    public ResourcesView(){

//      TextArea textArea = new TextArea("Do you need help finding extra resources? Here are some resources you can use:");
//      textArea.setPlaceholder("Write here ...");

        //TextField textField = new TextField("Do you need help finding extra resources? Here are some resources you can use:");
        Paragraph intro = new Paragraph("Do you need help finding extra resources? Here are some resources you can use:");
        //Label label = new Label("Do you need help finding extra resources? Here are some resources you can use:");
        add(intro);

        Paragraph generalResources = new Paragraph("General Resources:");
        add(generalResources);

        Anchor khan = new Anchor("https://www.khanacademy.org/", "Khan Academy");
        add(khan);

        Anchor quizlet = new Anchor("https://www.quizlet.com/", "Quizlet");
        add(quizlet);

        Anchor collegeboard = new Anchor("https://www.collegeboard.org/", "Collegeboard");
        add(collegeboard);

        Anchor hiemile = new Anchor("https://hiemile.com/", "Emile");
        add(hiemile);

        Anchor ixl = new Anchor("https://www.ixl.com/", "IXL");
        add(ixl);


        Paragraph space = new Paragraph(" ");
        add(space);

        Paragraph youtube = new Paragraph("Youtube Resources:");
        add(youtube);


        Paragraph generalChannels = new Paragraph("General Youtube Channels:");
        add(generalChannels);


        Anchor crashYT = new Anchor("https://www.youtube.com/user/crashcourse", "Youtube - Crash Course");
        add(crashYT);

        Anchor APYT = new Anchor("https://www.youtube.com/user/advancedplacement", "Youtube - Advanced Placement Daily Videos");
        add(APYT);

        Anchor princeton = new Anchor("https://www.youtube.com/c/ThePrincetonReview/videos", "Youtube - The Princeton Review");
        add(princeton);

        Anchor albertio = new Anchor("https://www.youtube.com/c/Albertio/featured", "Youtube - Albert.io");
        add(albertio);

        Anchor brainpop = new Anchor("https://www.youtube.com/c/brainpop/featured", "Youtube - Brain Pop");
        add(brainpop);



        Paragraph space1 = new Paragraph(" ");
        add(space1);

        Paragraph mathandsciChannels = new Paragraph("Math and Science Youtube Channels:");
        add(mathandsciChannels);

        Anchor khanYT = new Anchor("https://www.youtube.com/c/khanacademy/videos", "Youtube - Khanacademy");
        add(khanYT);

        Anchor organic = new Anchor("https://www.youtube.com/channel/UCEWpbFLzoYGPfuWUMFPSaoA", "Youtube - Organic Chemistry Tutor");
        add(organic);


        Paragraph space2 = new Paragraph(" ");
        add(space2);

        Paragraph mathChannels = new Paragraph("Math Youtube Channels:");
        add(mathChannels);

        Anchor krista = new Anchor("https://www.youtube.com/channel/UCUDlvPp1MlnegYXOXzj7DEQ", "Youtube - Krista King");
        add(krista);

        Anchor patrickJMT = new Anchor("https://www.youtube.com/user/patrickJMT", "Youtube - patrickJMT");
        add(patrickJMT);

        Anchor mathantics = new Anchor("https://www.youtube.com/user/mathantics/videos", "Youtube - mathantics");
        add(mathantics);

        Anchor blackpenredpen = new Anchor("https://www.youtube.com/c/blackpenredpen/playlists", "Youtube - blackpenredpen");
        add(blackpenredpen);



        Paragraph space3 = new Paragraph(" ");
        add(space3);

        Paragraph scienceChannels = new Paragraph("Science Youtube Channels:");
        add(scienceChannels);

        Anchor boze = new Anchor("https://www.youtube.com/bozemanscience/videos", "Youtube - Bozeman Science");
        add(boze);

        Anchor amoe = new Anchor("https://www.youtube.com/user/AmoebaSisters", "Youtube - Amoeba Sisters");
        add(amoe);

        Anchor prof = new Anchor("https://www.youtube.com/c/ProfessorDaveExplains/playlists", "Youtube - Professor Dave Explains");
        add(prof);



        Paragraph space4 = new Paragraph(" ");
        add(space4);

        Paragraph csChannels = new Paragraph("Computer Science Youtube Channels:");
        add(csChannels);

        Anchor mosh = new Anchor("https://www.youtube.com/user/programmingwithmosh", "Youtube - Programming with Mosh");
        add(mosh);

        Anchor APCSCB = new Anchor("https://www.youtube.com/watch?v=fQ6bxFQFldQ&list=PLoGgviqq4845xKOY11PnkE7aqJC7-bYrd", "Youtube - CollegeBoard: AP Computer Science");
        add(APCSCB);

        Anchor freeCodeCamp = new Anchor("https://www.youtube.com/channel/UC8butISFwT-Wl7EV0hUK0BQ", "Youtube - freeCodeCamp");
        add(freeCodeCamp);



        Paragraph space5 = new Paragraph(" ");
        add(space5);

        Paragraph web = new Paragraph("Websites:");
        add(web);



        Paragraph english = new Paragraph("English Language Arts Resources:");
        add(english);

        Anchor spark = new Anchor("https://www.sparknotes.com/", "Sparknotes");
        add(spark);

        Anchor quizlet2 = new Anchor("https://www.quizlet.com/", "Quizlet");
        add(quizlet2);

        Anchor everfi = new Anchor("https://get.everfi.com/k12-remote-learning/?utm_source=google&utm_medium=cpc&utm_campaign=K12%20-%20Educational%20Resources&utm_term=teacher%20resources&utm_content=434272073701&_bk=teacher%20resources&_bt=434272073701&_bm=b&_bn=g&source=7013g000000PpQs&gclid=Cj0KCQjw78yFBhCZARIsAOxgSx23DPQea_cDGXnoDnrO1_puAieXtNyqBrSOs__PfxheoVqp62gSILAaAsW_EALw_wcB", "Everfi");
        add(everfi);

        Anchor ixlE = new Anchor("https://www.ixl.com/ela/", "IXL - ELA");
        add(ixlE);



        Paragraph space6 = new Paragraph(" ");
        add(space6);

        Paragraph foreignLang = new Paragraph("Foreign-Language Resources:");
        add(foreignLang);

        Anchor quizlet3 = new Anchor("https://www.quizlet.com/", "Quizlet");
        add(quizlet3);

        Anchor duolingo = new Anchor("https://www.duolingo.com/", "Duolingo");
        add(duolingo);

        Anchor clozemaster = new Anchor("https://apps.apple.com/us/app/clozemaster/id1149199075?ls=1", "App - Clozemaster");
        add(clozemaster);

        Anchor ixlS = new Anchor("https://www.ixl.com/spanish/", "IXL - Spanish");
        add(ixlS);




        Paragraph space7 = new Paragraph(" ");
        add(space7);

        Paragraph science = new Paragraph("Science Resources:");
        add(science);

        Anchor ixlSc = new Anchor("https://www.ixl.com/science/", "IXL - Science");
        add(ixlSc);

        Anchor openS = new Anchor("https://openstax.org/", "Openstax - Online Science Textbooks");
        add(openS);

        Anchor boze2 = new Anchor("https://www.youtube.com/bozemanscience/videos", "Youtube - Bozeman Science");
        add(boze2);

        Anchor amoe2 = new Anchor("https://www.youtube.com/user/AmoebaSisters", "Youtube - Amoeba Sisters");
        add(amoe2);

        Anchor apbio = new Anchor("https://www.khanacademy.org/science/ap-biology", "Khanacademy - AP Biology");
        add(apbio);

        Anchor aphysics = new Anchor("https://www.khanacademy.org/science/ap-physics-1", "Khanacademy - AP Physics");
        add(aphysics);

        Anchor apchem = new Anchor("https://www.khanacademy.org/science/ap-chemistry", "Khanacademy - AP Chemistry");
        add(apchem);

        Anchor brainpop2 = new Anchor("https://www.brainpop.com/science/seeall/", "BrainPOP");
        add(brainpop2);

        Anchor brightstorm = new Anchor("https://www.khanacademy.org/science/ap-chemistry", "Brightstorm AP Biology Videos");
        add(brightstorm);

        Anchor biozone = new Anchor("https://www.brightstorm.com/test-prep/ap-biology/ap-biology-videos/", "Biozone");
        add(biozone);




        Paragraph space8 = new Paragraph(" ");
        add(space8);

        Paragraph math = new Paragraph("Math Resources:");
        add(math);

        Anchor purple = new Anchor("https://www.purplemath.com/", "Purplemath");
        add(purple);

        Anchor ixlM = new Anchor("https://www.ixl.com/math/", "IXL - Math");
        add(ixlM);

        Anchor openM = new Anchor("https://openstax.org/", "Openstax - Online Math Textbooks");
        add(openM);

        Anchor deltamath = new Anchor("https://www.deltamath.com/", "Deltamath");
        add(deltamath);

        Anchor khanMM = new Anchor("https://www.khanacademy.org/", "Khanacdemy - Math Courses");
        add(khanMM);

        Anchor krista2 = new Anchor("https://www.youtube.com/channel/UCUDlvPp1MlnegYXOXzj7DEQ", "Youtube - Krista King");
        add(krista2);

        Anchor patrickJMT2 = new Anchor("https://www.youtube.com/user/patrickJMT", "Youtube - patrickJMT");
        add(patrickJMT2);

        Anchor mathantics2 = new Anchor("https://www.youtube.com/user/mathantics/videos", "Youtube - mathantics");
        add(mathantics2);

        Anchor blackpenredpen2 = new Anchor("https://www.youtube.com/c/blackpenredpen/playlists", "Youtube - blackpenredpen");
        add(blackpenredpen2);



        Paragraph space9 = new Paragraph(" ");
        add(space9);

        Paragraph history = new Paragraph("Social Studies Resources:");
        add(history);

        Anchor khanAPPUSH = new Anchor("https://www.khanacademy.org/humanities/ap-us-history", "Khanacademy - APPUSH");
        add(khanAPPUSH);

        Anchor crash = new Anchor("https://www.youtube.com/user/crashcourse", "Youtube - Crash Course");
        add(crash);

        Anchor historylink = new Anchor("https://www.history.com/", "History.com");
        add(historylink);

        Anchor ixlH = new Anchor("https://www.ixl.com/social-studies/", "IXL - Social Studies");
        add(ixlH);

        Anchor brainpop3 = new Anchor("https://www.brainpop.com/science/seeall/", "BrainPOP");
        add(brainpop3);


        Paragraph space10 = new Paragraph(" ");
        add(space10);

        Paragraph cs = new Paragraph("Computer Science Resources:");
        add(cs);

        Anchor moshR = new Anchor("https://www.youtube.com/user/programmingwithmosh", "Youtube - Programming with Mosh");
        add(moshR);

        Anchor CBAPCS = new Anchor("https://apstudents.collegeboard.org/courses/ap-computer-science-a", "College Board - AP Computer Science");
        add(CBAPCS);

        Anchor codingbat = new Anchor("https://codingbat.com/java", "Codingbat");
        add(codingbat);

        Anchor practiceit = new Anchor("https://practiceit.cs.washington.edu/", "Practice-it");
        add(practiceit);

        Anchor freecodecamp = new Anchor("https://www.freecodecamp.org/", "freeCodeCamp");
        add(freecodecamp);


        Paragraph space11 = new Paragraph(" ");
        add(space11);

        Paragraph sat = new Paragraph("SAT/ACT Resources:");
        add(sat);

        Anchor khanSat = new Anchor("https://www.khanacademy.org/sat", "Khanacademy - SAT");
        add(khanSat);

        Anchor CBsat = new Anchor("https://collegereadiness.collegeboard.org/sat", "College Board - SAT");
        add(CBsat);

        Anchor ryanChoice = new Anchor("https://www.youtube.com/channel/UCpaLCn_uFCdbWEIM6e0XOjA", "Youtube - Ryan CHOICE");
        add(ryanChoice);

        Anchor ryChoi = new Anchor("https://www.twitch.tv/ryan_choice", "Twitch - Ryan_CHOICE");
        add(ryChoi);

        Anchor prepScholar = new Anchor("https://www.prepscholar.com/sat/s/", "PrepScholar SAT/ACT Blogs <-- highly recommend");
        add(prepScholar);

        Anchor erica = new Anchor("https://www.amazon.com/Critical-Reader-Fourth-Complete-Reading/dp/173358952X/ref=sr_1_1_sspa?dchild=1&gclid=Cj0KCQjw78yFBhCZARIsAOxgSx3SLDKwiQ_PVreDRiMsr1ToawHAokMTTWeWepvC9MoQZ2jnVwUqX0EaAhJnEALw_wcB&hvadid=241634839346&hvdev=c&hvlocphy=9002019&hvnetw=g&hvqmt=e&hvrand=10709494842868525930&hvtargid=kwd-242132674327&hydadcr=22565_10355049&keywords=erica+meltzer+sat+reading&qid=1622407819&sr=8-1-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEyUVFQUEhRNlEyNFNQJmVuY3J5cHRlZElkPUEwMDEyMTY3MlRLOFg5QkZJTzlNSCZlbmNyeXB0ZWRBZElkPUEwMzQ2NTQ2M0owTDdHMVhBU1VJVCZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU=", "Textbook - SAT Reading and Writing by Erica Meltzer");
        add(erica);

        Anchor panda = new Anchor("https://www.amazon.com/College-Pandas-SAT-Math-Advanced/dp/0989496422/ref=sr_1_1?crid=180VVCDANW2VC&dchild=1&keywords=panda+sat+math&qid=1622407897&sprefix=panda+sat%2Caps%2C159&sr=8-1", "Textbook -  The College Panda: Sat Math");
        add(panda);



        Paragraph space12 = new Paragraph(" ");
        add(space12);

        Paragraph tutoringCenters = new Paragraph("Local Tutoring Centers:");
        add(tutoringCenters);

        Anchor kumon = new Anchor("https://www.kumon.com/", "Kumon");
        add(kumon);

        Anchor rsm = new Anchor("https://www.russianschool.com/", "Russian School of Mathematics");
        add(rsm);

        Anchor mathnasium = new Anchor("https://www.mathnasium.com/", "Mathnasium");
        add(mathnasium);

        Anchor hlc = new Anchor("https://huntingtonhelps.com/", "Huntington Learning Center");
        add(hlc);

        Paragraph space13 = new Paragraph(" ");
        add(space13);
    }
}


