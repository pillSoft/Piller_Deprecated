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

public class IntentActivityManager {

        private static String[] intentArray = {"Eventi", "Meteo", "Prova1", "Notizie", "prova2", "prova3"};
        private static String loremIpsum = "Informazioni sulla card";

        private static IntentActivityManager mInstance;
        private List<Theme> listTheme;

        public static IntentActivityManager getInstance() {
            if (mInstance == null) {
                mInstance = new IntentActivityManager();
            }

            return mInstance;
        }

        public List<Theme> getCountries() {
            return  MainActivity.ThemeList;
        }

    }

