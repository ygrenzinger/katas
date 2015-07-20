package GuildedRose;

import org.assertj.core.api.StrictAssertions;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by yannickgrenzinger on 28/06/2015.
 */
public class GildedRoseTest {

    @Test
    //At the end of each day our system lowers both quality and sellIn for every item
    public void endOfTheDay() {
        Item[] items =  new Item[] { new Item("foo1", 1, 1), new Item("foo2", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        Item item = app.items[0];
        assertThat(item.name).isEqualTo("foo1");
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(0);

        item = app.items[1];
        assertThat(item.name).isEqualTo("foo2");
        assertThat(item.quality).isEqualTo(1);
        assertThat(item.sellIn).isEqualTo(1);
    }

    @Test
    //Once the sell by date has passed, Quality degrades twice as fast
    //the quality is never negative
    public void onceTheSellDateHasPassed() {
        Item[] items =  new Item[] { new Item("foo1", 1, 5) };

        GildedRose app = new GildedRose(items);
        Item item = app.items[0];
        app.updateQuality();
        assertThat(item.name).isEqualTo("foo1");
        assertThat(item.quality).isEqualTo(4);
        assertThat(item.sellIn).isEqualTo(0);
        app.updateQuality();
        assertThat(item.quality).isEqualTo(2);
        assertThat(item.sellIn).isEqualTo(-1);
        app.updateQuality();
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-2);
        app.updateQuality();
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-3);
    }

    @Test
    //Once the sell by date has passed, Quality degrades twice as fast
    //the quality is never negative
    public void forConjured() {
        Item[] items =  new Item[] { new Item("Conjured", 1, 12) };

        GildedRose app = new GildedRose(items);
        Item item = app.items[0];
        app.updateQuality();
        assertThat(item.name).isEqualTo("Conjured");
        assertThat(item.quality).isEqualTo(10);
        assertThat(item.sellIn).isEqualTo(0);
        app.updateQuality();
        assertThat(item.quality).isEqualTo(6);
        assertThat(item.sellIn).isEqualTo(-1);
        app.updateQuality();
        assertThat(item.quality).isEqualTo(2);
        assertThat(item.sellIn).isEqualTo(-2);
        app.updateQuality();
        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-3);
    }

    @Test
    //"Aged Brie" actually increases in Quality the older it gets
    //the quality of item is never more than 50
    public void forItemAgedBrie() {
        Item[] items =  new Item[] { new Item("Aged Brie", 2, 2) };

        GildedRose app = new GildedRose(items);
        Item item = app.items[0];
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertThat(item.quality).isEqualTo(8);
        assertThat(item.sellIn).isEqualTo(-2);

        IntStream.range(0, 40).forEach(i -> {
            app.updateQuality();
        });

        assertThat(item.quality).isEqualTo(50);
        assertThat(item.sellIn).isEqualTo(-42);

    }

    @Test
    //"Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    public void forItemSulfuras() {
        Item[] items =  new Item[] { new Item("Sulfuras, Hand of Ragnaros", 400, 80) };

        GildedRose app = new GildedRose(items);
        Item item = app.items[0];
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();

        assertThat(item.quality).isEqualTo(80);
        assertThat(item.sellIn).isEqualTo(400);
    }

    @Test
    //"Backstage passes", like aged brie, increases in Quality as it's SellIn value approaches;
    //Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
    //Quality drops to 0 after the concert
    public void forItemBackstagePasses() {
        int quality = 10;
        Item[] items =  new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 12, quality) };

        GildedRose app = new GildedRose(items);
        Item item = app.items[0];
        app.updateQuality();
        app.updateQuality();

        quality = quality + 2;
        assertThat(item.quality).isEqualTo(quality);
        assertThat(item.sellIn).isEqualTo(10);

        IntStream.range(0, 5).forEach(i -> {
            app.updateQuality();
        });

        quality = quality + 5*2;
        assertThat(item.quality).isEqualTo(quality);
        assertThat(item.sellIn).isEqualTo(5);

        IntStream.range(0, 5).forEach(i -> {
            app.updateQuality();
        });

        quality = quality + 5*3;
        assertThat(item.quality).isEqualTo(quality);
        assertThat(item.sellIn).isEqualTo(0);

        app.updateQuality();

        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-1);

        app.updateQuality();

        assertThat(item.quality).isEqualTo(0);
        assertThat(item.sellIn).isEqualTo(-2);
    }

}