/*
 * Copyright (c) 2021, Matthias Bläsing
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.gnome.evolution.dataserver;

import at.yawk.dbus.client.annotation.Bus;
import at.yawk.dbus.client.annotation.Call;
import at.yawk.dbus.client.annotation.Destination;
import at.yawk.dbus.client.annotation.Interface;
import at.yawk.dbus.client.annotation.Member;
import at.yawk.dbus.client.annotation.ObjectPath;

@Interface("org.gnome.evolution.dataserver.CalendarFactory")
@Destination("org.gnome.evolution.dataserver.Calendar8")
@Bus("session")
@ObjectPath("/org/gnome/evolution/dataserver/CalendarFactory")
public interface CalendarFactory {
    
    @Call
    @Member("OpenCalendar")
    DBUSTarget OpenCalendar(String sourceUid);
    
    @Call
    @Member("OpenMemoList")
    DBUSTarget OpenMemoList(String sourceUid);
    
    @Call
    @Member("OpenTaskList")
    DBUSTarget OpenTaskList(String sourceUid);
}
