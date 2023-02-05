package net.kigawa.hakoniwa;

import org.bukkit.plugin.java.*;
import org.bukkit.plugin.java.annotation.dependency.*;
import org.bukkit.plugin.java.annotation.plugin.*;
import org.bukkit.plugin.java.annotation.plugin.author.*;

@Plugin(name = "HakoniwaCore", version = "1.0.0")
@Author("kigawa.net")
@Dependency("CommandAPI")
@Dependency("WorldGuard")
public class HakoniwaCore extends JavaPlugin
{
    @Override
    public void onEnable()
    {
    }

    @Override
    public void onDisable()
    {
    }
}
