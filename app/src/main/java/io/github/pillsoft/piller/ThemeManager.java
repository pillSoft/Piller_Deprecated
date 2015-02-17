/*
This file is part of Piller.

Piller is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Piller is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Piller. If not, see <http://www.gnu.org/licenses/>.

Copyright 2015, Giulio Fagioli, Lorenzo Salani
*/
package io.github.pillsoft.piller;

import java.util.List;

public class ThemeManager {

        private static ThemeManager mInstance;
        private List<Theme> listTheme;

        public static ThemeManager getInstance() {
            if (mInstance == null) {
                mInstance = new ThemeManager();
            }

            return mInstance;
        }

        public List<Theme> getListTheme() {
            return  MainActivity.ThemeList;
        }

    }

