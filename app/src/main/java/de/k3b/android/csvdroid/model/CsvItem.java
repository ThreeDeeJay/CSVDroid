/*
Copyright (C) 2023 by k3b

This file is part of CSVDroid (https://github.com/k3b/CSVDroid)

This program is free software: you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU General Public License
for more details.

You should have received a copy of the GNU General Public License along with
this program. If not, see <http://www.gnu.org/licenses/>
 */
package de.k3b.android.csvdroid.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evrencoskun.tableview.model.IModelWithId;

/** A {@link CsvItem} represents one item (line) of a csv file.
 * * An item (line) consists of comments or empty lines above the columns
 * * The columns that contain the csv data
 * * The linenumber of the csv-file where the CsvItem was read from.
 *
 * Created by k3b on 2023-03-25
 */
public class CsvItem  implements IModelWithId {
    @NonNull private final String id;
    @Nullable private final CsvItem header;
    final int linenumber;
    @NonNull final String[] columns;
    @Nullable final String[] comments;

    public CsvItem(@Nullable CsvItem header, int linenumber, @NonNull String[] columns,@Nullable String[] comments) {
        this.id = "" + linenumber;
        this.linenumber = linenumber;
        this.columns = columns;
        this.comments = comments;
        this.header = header;
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @NonNull
    public String getColumn(int colNo, String notFoundValue) {
        // if there is no value return ""
        if (colNo < 0 || colNo >= columns.length
                || (header != null && colNo >= header.columns.length)
                || columns[colNo] == null) {
            return notFoundValue;
        }

        return columns[colNo];
    }

    @NonNull
    public String getComments() {
        if (this.comments == null || this.comments.length == 0) return "";
        return String.join("\n", comments);
    }

    public int getColumnCount() {
        return columns.length;
    }
}
