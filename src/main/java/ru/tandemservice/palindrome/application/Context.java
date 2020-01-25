package ru.tandemservice.palindrome.application;

import ru.tandemservice.palindrome.bd.ConversionRateRepo;
import ru.tandemservice.palindrome.bd.PhraseRepo;
import ru.tandemservice.palindrome.bd.ScoringRepo;
import ru.tandemservice.palindrome.bd.UserRepo;
import ru.tandemservice.palindrome.bd.impl.ConversionRateRamImpl;
import ru.tandemservice.palindrome.bd.impl.PhraseRepoRamImpl;
import ru.tandemservice.palindrome.bd.impl.ScoringRepoRamImpl;
import ru.tandemservice.palindrome.bd.impl.UserRepoRamImpl;
import ru.tandemservice.palindrome.bh.Game;
import ru.tandemservice.palindrome.bh.PalindromeVerifier;
import ru.tandemservice.palindrome.bh.PointNumberCalculator;
import ru.tandemservice.palindrome.service.ScoringService;
import ru.tandemservice.palindrome.service.UserService;
import ru.tandemservice.palindrome.ui.UI;
import ru.tandemservice.palindrome.ui.impl.ConsoleInput;

import java.util.HashMap;

@SuppressWarnings("WeakerAccess")
public class Context {
    private static Context context;
    private HashMap<String, Object> objectMap;

    private Context() {
        this.objectMap = new HashMap<>();
    }

    public static Context getContext() {
        if (null == context) {
            context = new Context();
        }
        return context;
    }

    public static void main(String[] args) {
        Context context = Context.getContext();
        UI ui = context.getUi();
        ui.run();
    }

    private UI getUi() {
        UI ui = (UI) objectMap.computeIfAbsent("ui", (k) -> new ConsoleInput());
        ui.setGame(getGame());
        return ui;
    }

    public Game getGame() {
        final UserService userService = getUserService();
        final ScoringService scoringService = getScoringService();
        PalindromeVerifier palindromeVerifier = getPalindromeVerifier();
        return (Game) objectMap.computeIfAbsent("game",
                (k) -> new Game(userService, scoringService, palindromeVerifier));
    }

    public UserService getUserService() {
        final UserRepo userRepo = getUserRepo();
        return (UserService) objectMap.computeIfAbsent("userService", (k) -> new UserService(userRepo));
    }

    public UserRepo getUserRepo() {
        return(UserRepo) objectMap.computeIfAbsent("userRepo", (k) -> new UserRepoRamImpl());
    }

    public ScoringService getScoringService() {
        final ScoringRepo scoringRepo = getScoringRepo();
        final PhraseRepo phraseRepo = getPhraseRepo();
        final ConversionRateRepo conversionRateRepo = getConversionRateRepo();
        final PointNumberCalculator pointNumberCalculator = getPointNumberCalculator();

        return (ScoringService) objectMap.computeIfAbsent("scoringService",
                (k) -> new ScoringService(scoringRepo, phraseRepo, conversionRateRepo, pointNumberCalculator));
    }

    public ScoringRepo getScoringRepo() {
       return (ScoringRepo) objectMap.computeIfAbsent("scoringRepo", (k) -> new ScoringRepoRamImpl());
    }

    public PhraseRepo getPhraseRepo() {
        return (PhraseRepo) objectMap.computeIfAbsent("phraseRepo", (k) -> new PhraseRepoRamImpl());
    }

    public ConversionRateRepo getConversionRateRepo() {
        return (ConversionRateRepo) objectMap.computeIfAbsent("conversionRateRepo", (k) -> new ConversionRateRamImpl());
    }

    public PalindromeVerifier getPalindromeVerifier() {
        return (PalindromeVerifier) objectMap.computeIfAbsent("palindromeVerifier", (k) -> new PalindromeVerifier());
    }


    public PointNumberCalculator getPointNumberCalculator() {
        return (PointNumberCalculator) objectMap.computeIfAbsent("pointNumberCalculator",
                (k) -> new PointNumberCalculator());
    }
}
