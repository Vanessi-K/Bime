package com.example.bime

//how to use string with colour?
class Category(val id: Int? = null, var name: String? = "", var colour: String? = "") {
    override fun toString(): String {
        return "Category_id: ${id}, Category_name: ${name}, Category_colour: ${colour}";
    }
}