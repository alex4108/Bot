/*
 * Groovy Bot - The core component of the Groovy Discord music bot
 *
 * Copyright (C) 2018  Oskar Lang & Michael Rittmeister & Sergeij Herdt & Yannick Seeger & Justus Kliem & Leon Kappes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 */

package co.groovybot.bot.commands.general;

import co.groovybot.bot.core.command.Command;
import co.groovybot.bot.core.command.CommandCategory;
import co.groovybot.bot.core.command.CommandEvent;
import co.groovybot.bot.core.command.Result;
import co.groovybot.bot.core.command.permission.Permissions;

public class InviteCommand extends Command {
    public InviteCommand() {
        super(new String[]{"invite", "inv"}, CommandCategory.GENERAL, Permissions.everyone(), "Shows you an invite for Groovy", "[-absolute]");
    }

    @Override
    public Result run(String[] args, CommandEvent event) {
        return send(small(String.format("**[%s](https://discordapp.com/oauth2/authorize?client_id=%s&permissions=1744170353&scope=bot&response_type=code&redirect_uri=%s)**", event.translate("command.invite"), event.getJDA().getSelfUser().getId(), "https%3A%2F%2Fgroovybot.co%2Fthanks")));
    }
}
