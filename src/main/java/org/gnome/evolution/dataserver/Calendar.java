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

import at.yawk.dbus.client.annotation.Call;
import at.yawk.dbus.client.annotation.GetProperty;
import at.yawk.dbus.client.annotation.Interface;
import at.yawk.dbus.client.annotation.Member;
import at.yawk.dbus.databind.annotation.Struct;
import at.yawk.dbus.databind.annotation.StructMember;

@Interface("org.gnome.evolution.dataserver.Calendar")
public interface Calendar {

    @Struct
    public static class CalendarIdentifier {
        private String uid;
        private String rid;

        public CalendarIdentifier() {
        }
        
        public CalendarIdentifier(String uid, String rid) {
            this.uid = uid;
            this.rid = rid;
        }

        @StructMember(position = 0)
        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        @StructMember(position = 1)
        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }
    }
    
    @Member("Close")
    @Call
    public void Close();
    
    @Member("Open")
    @Call
    public String[] Open();
    
    @Member("Refresh")
    @Call
    public void Refresh();
    
    @Member("RetrieveProperties")
    @Call
    public String[] RetrieveProperties();
    
    @Member("CreateObjects")
    @Call
    public String[] CreateObjects(String[] vcards);
    
    @Member("GetObject")
    @Call
    public String GetObject(String uid, String rid);
    
    @Member("GetObjectList")
    @Call
    public String[] GetObjectList(String query);
    
    @Member("ModifyObjects")
    @Call
    public void ModifyObjects(String[] vcards, String mod_type);
    
    @Member("RemoveObjects")
    @Call
    public void RemoveObjects(CalendarIdentifier[] uids, String mod_type);
    
    @Member("Capabilities")
    @GetProperty
    public String[] getCapabilities();
    
    @Member("Online")
    @GetProperty
    public boolean isOnline();
    
    @Member("Writable")
    @GetProperty
    public boolean isWritable();

    @Member("AlarmEmailAddress")
    @GetProperty
    public String getAlarmEmailAddress();
    
    @Member("CacheDir")
    @GetProperty
    public String getCacheDir();
    
    @Member("CalEmailAddress")
    @GetProperty
    public String getCalEmailAddress();

    @Member("DefaultObject")
    @GetProperty
    public String getDefaultObject();
    
    @Member("Revision")
    @GetProperty
    public String Revision();
}
