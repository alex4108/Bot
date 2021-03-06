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

package co.groovybot.bot.commands.owner;

import co.groovybot.bot.core.command.Command;
import co.groovybot.bot.core.command.CommandCategory;
import co.groovybot.bot.core.command.CommandEvent;
import co.groovybot.bot.core.command.Result;
import co.groovybot.bot.core.command.permission.Permissions;
import co.groovybot.bot.util.EmbedUtil;
import co.groovybot.bot.util.SafeMessage;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.react.GuildMessageReactionAddEvent;

public class CloseCommand extends Command {

    public CloseCommand() {
        super(new String[]{"close", "stopbot", "shutdown"}, CommandCategory.DEVELOPER, Permissions.ownerOnly(), "Lets you close Groovy", "");
    }

    @Override
    public Result run(String[] args, CommandEvent event) {
        Message confirmMessage = SafeMessage.sendMessageBlocking(event.getChannel(), EmbedUtil.small(event.translate("command.close.confirmation")));
        confirmMessage.addReaction("✅").queue();
        confirmMessage.addReaction("❌").queue();
        event.getBot().getEventWaiter().waitForEvent(GuildMessageReactionAddEvent.class, e -> confirmMessage.getIdLong() == e.getMessageIdLong() && e.getGuild().equals(event.getGuild()) && !e.getUser().isBot(),
                e -> {

                    if (e.getReactionEmote().getName().equals("✅")) {
                        SafeMessage.sendMessageBlocking(event.getChannel(), success(event.translate("phrases.success"), event.translate("command.close")));
                        confirmMessage.delete().complete();
                        System.exit(0);
                    }

                    confirmMessage.clearReactions().queue();
                    SafeMessage.editMessage(confirmMessage, small(event.translate("command.close.cancel")));
                });

        return null;
    }
}
