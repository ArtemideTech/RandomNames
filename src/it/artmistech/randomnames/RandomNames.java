package it.artmistech.randomnames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class RandomNames extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("randomname").setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = getConfig();

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("random.names")) {
                Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
                    String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                    StringBuilder builder = new StringBuilder();
                    int c = 10;

                    while (c-- != 0) {
                        int index = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
                        builder.append(ALPHA_NUMERIC_STRING.charAt(index));
                    }

                    onlinePlayer.setCustomName(builder.toString());
                    onlinePlayer.setDisplayName(builder.toString());
                });
            } else {
                player.sendMessage(ChatColor.RED + "No permission for this command");
            }
        }

        return true;
    }
}
