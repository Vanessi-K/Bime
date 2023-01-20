package com.example.bime.model

class Category(val id: Int, var name: String = "", var colour: String = "#FFFFFF") {
    override fun toString(): String {
        return "Category_id: ${id}, Category_name: ${name}, Category_colour: ${colour}"
    }
}