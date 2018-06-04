package Comands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class Status implements CommandExecutor, Listener {

    public static String GetRamUsage(){


        Runtime run = Runtime.getRuntime();


        long freeMem = run.freeMemory() / 1048576L;
        long MaxMem = run.maxMemory() / 1048576L;
        long usedMem = MaxMem - freeMem;

        float percent = usedMem / MaxMem;

        String ret = "§7 [ " + usedMem + " §aMB§7/" + MaxMem + " §bMB §a(" + Math.floor(percent) + "%) §7]";

        return ret;


    }

    public static String GetRamUsageBar() {

        Runtime run = Runtime.getRuntime();

        long freeMem = run.freeMemory() / 1048576L;
        long maxMem = run.maxMemory() / 1048576L;

        long usedMem = maxMem - freeMem;

        float percent = usedMem / maxMem;

        int steps = (int)(percent / 5.0F);
        String Membar = "§r§2";
        char color = 'c';

        for(int i = 0; i <= 10; i++) {
            if(i <= steps) {
                color = 'e';
            }
            Membar  = String.valueOf(Membar) + "§" + color + "⬛";
        }
        return Membar;

    }

    public static void PlayerInv(final Player p) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 45, "§eStatus do Servidor:");


        /* getRamUsage()*/
        ItemStack g = new ItemStack(Material.BANNER, 1, (short)2);
        ItemMeta gmeta = g.getItemMeta();
        gmeta.setDisplayName(" §fMemória RAM: §7" + GetRamUsage());
        g.setItemMeta(gmeta);
        inv.setItem(11, g);

        /* getBarRamUsage() */
        ItemStack bar = new ItemStack(Material.MAP);
        ItemMeta barmeta = bar.getItemMeta();
        barmeta.setDisplayName("§f " + GetRamUsageBar());
        bar.setItemMeta(barmeta);
        inv.setItem(21, bar);

        /* Status */
        ItemStack s = new ItemStack(Material.EMPTY_MAP);
        ItemMeta smeta = s.getItemMeta();
        smeta.setDisplayName(" §fStatus: §aONLINE");
        s.setItemMeta(smeta);
        inv.setItem(13, s);

        /* Processador */
        ItemStack pr = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta prMeta = pr.getItemMeta();
        prMeta.setDisplayName(" §fProcessadores CPU: §e" + Runtime.getRuntime().availableProcessors());
        pr.setItemMeta(prMeta);
        inv.setItem(15, pr);

        /* Sistema Operacional */
        ItemStack so = new ItemStack(Material.FIREWORK_CHARGE);
        ItemMeta soMeta = so.getItemMeta();
        soMeta.setDisplayName("§f Sistema Operacional: §b" + System.getProperty("os.name"));
        so.setItemMeta(soMeta);
        inv.setItem(23, so);

        /* Versão S */
        ItemStack v = new ItemStack(Material.DISPENSER);
        ItemMeta vMeta = v.getItemMeta();
        vMeta.setDisplayName("§f Versão do Spigot: §e" + Bukkit.getBukkitVersion());
        v.setItemMeta(vMeta);
        inv.setItem(31, v);

        // Jogadores Online //
        ItemStack jo = new ItemStack(Material.COMPASS);
        ItemMeta joMeta = jo.getItemMeta();
        joMeta.setDisplayName("§f Jogadores Online: §b" + Bukkit.getOnlinePlayers().size() + "§7/§b" + Bukkit.getMaxPlayers());
        jo.setItemMeta(joMeta);
        inv.setItem(33, jo);

        // Versão //
        ItemStack vs = new ItemStack(Material.BONE);
        ItemMeta vsMeta = vs.getItemMeta();
        vsMeta.setDisplayName("§f Versão: §a" + Bukkit.getVersion());
        vs.setItemMeta(vsMeta);
        inv.setItem(29, vs);


        p.openInventory(inv);
    }

    @EventHandler
    public void Inventory(final InventoryClickEvent e) {

        if(e.getCurrentItem() == null) {
            return;
        }

        if(e.getInventory().getTitle().contains("§eStatus do Servidor:")) {
            e.setCancelled(true);
        }

    }

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

        Player p = (Player)arg0;

        if(arg1.getName().equalsIgnoreCase("status")) {
            if(p.hasPermission("eclipse.gerente")) {

                PlayerInv(p);

                return true;
            } else {
                p.sendMessage("§cVocê não tem permissão para ver os status do servidor.");
            }
        }
        return false;
    }

}