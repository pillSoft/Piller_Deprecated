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

class Theme {

    String theme_name;
    String theme_package;
    String theme_color;
    String theme_darkcolor;
    String theme_accentcolor;
    String theme_highlightedcolor;
    String theme_motto;


    public Theme(String theme_name, String theme_package, String theme_color, String theme_darkcolor, String theme_accentcolor, String theme_highlightedcolor, String theme_motto) {
        this.theme_name = theme_name;
        this.theme_package = theme_package;
        this.theme_color = theme_color;
        this.theme_darkcolor = theme_darkcolor;
        this.theme_accentcolor = theme_accentcolor;
        this.theme_highlightedcolor = theme_highlightedcolor;
        this.theme_motto = theme_motto;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public String getTheme_package() {
        return theme_package;
    }

    public String getTheme_color() {
        return theme_color;
    }

    public String getTheme_motto() {
        return theme_motto;
    }

    public String [] getArrayListTheme() {
        return new String [] {theme_name, theme_package, theme_color, theme_darkcolor, theme_accentcolor, theme_highlightedcolor, theme_motto};
    }


}
