package Poker;

import java.util.Comparator;

/**
 * Created by yannickgrenzinger on 02/12/2015.
 */
public class PokerCardComparator implements Comparator<PokerCard> {
    @Override
    public int compare(PokerCard p1, PokerCard p2) {
        return p1.getRank().value() - p2.getRank().value();
    }
}
