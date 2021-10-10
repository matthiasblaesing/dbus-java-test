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
package eu.doppelhelix.dev.dbustest;

import at.yawk.dbus.client.DbusClient;
import java.io.IOException;
import org.gnome.evolution.dataserver.Calendar;
import org.gnome.evolution.dataserver.CalendarFactory;
import org.gnome.evolution.dataserver.DBUSTarget;

/**
 * This sample demonstrates accessing the evolution data server in version 3.40.0
 */
public class EvolutionExample {
    public static void main(String[] args) throws IOException, Exception {
        try (DbusClient client = new DbusClient()) {
            client.connectSession();
            
            // Basic call with a fixed DBUS Destination (bus, connection, path)
            CalendarFactory cf = client.implement(CalendarFactory.class);
            // OpenMemoList will return a struct with two members:
            //
            // - objectPath (member 1)
            // - destination (member 2)
            //
            // Together with the session bus this forms a full DBUSDestination
            // the structure is mappend onto a DBUSTarget with implements
            // DBUSDestination, so that it can directly be used in
            // DBUSClient#implement
            DBUSTarget ad = cf.OpenMemoList("system-memo-list");
            
            // Output the new DBUSDestination
            System.out.println("----------------------------------------");
            System.out.println(ad);

            // Create a proxy object based on the Calendar interface and the
            // dbus destination returned by OpenMemoList
            Calendar systemMemoList = client.implement(Calendar.class, ad);
            try {
                // Open the calendar, which returns a list of properties, which
                // are represented as key1, value1, key2, value2, ...
                System.out.println("----------------------------------------");
                String[] openResult = systemMemoList.Open();
                // Output the openResult as a key-value list
                for (int i = 0; i < openResult.length; i += 2) {
                    System.out.printf("%30s: %s%n", openResult[i], openResult[i + 1]);
                }
                System.out.println("----------------------------------------");
                // Run a query against the memory list and return all vjournal,
                // that contains a summary
                for (String vcard : systemMemoList.GetObjectList("(contains? \"summary\" \"\")")) {
                    // This is vjournal data in string format
                    System.out.println(vcard);
                }
            } finally {
                // Close the memo list
                systemMemoList.Close();
            }
        }
    }
}
