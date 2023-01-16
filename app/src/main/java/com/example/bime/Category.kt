package com.example.bime

class Category(var id: String? = null, var name: String? = "", var colour: String? = "") {
    override fun toString(): String {
        return "Category_id: ${id}, Category_name: ${name}, Category_colour: ${colour}";
    }
}