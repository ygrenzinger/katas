package GuildedRose;

/**
 * Created by yannickgrenzinger on 28/06/2015.
 */
public class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    private void updateItemQuality(Item item) {
        if (item.name.equals("Sulfuras, Hand of Ragnaros")) return; //Do nothing

        item.sellIn = item.sellIn - 1;

        switch (item.name) {
            case "Backstage passes to a TAFKAL80ETC concert":
                manageBackstagePasses(item);
                break;
            case "Aged Brie":
                manageAgedBrie(item);
                break;
            case "Conjured":
                item.quality = item.quality + qualityShiftForDefaultItem(item) * 2;
                break;
            default:
                item.quality = item.quality + qualityShiftForDefaultItem(item);
                break;
        }
        if (item.quality < 0) item.quality = 0;

    }

    private int qualityShiftForDefaultItem(Item item) {
        int qualityShift = 0;
        if (item.quality > 0) {
            qualityShift--;
            if (item.sellIn < 0) {
                qualityShift--;
            }
        }
        return qualityShift;
    }

    private void manageBackstagePasses(Item item) {
        if (item.quality < 50) {
            if (item.sellIn < 0) {
                item.quality = 0;
            } else if (item.sellIn < 5) {
                item.quality = item.quality + 3;
            } else if (item.sellIn < 10) {
                item.quality = item.quality + 2;
            } else {
                item.quality = item.quality + 1;
            }
        }
    }

    private void manageAgedBrie(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.sellIn < 0) {
                item.quality = item.quality + 1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)};

        GildedRose app = new GildedRose(items);

        int days = 2;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        for (int i = 0; i < days; i++) {
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
        }
    }
}
