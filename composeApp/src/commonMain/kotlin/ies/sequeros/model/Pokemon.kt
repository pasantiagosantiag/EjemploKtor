package ies.sequeros.model

data class Pokemon( val id:Int=-1, val name:String="", val href:String="", val image:String="",
                    var power:Int=(Math.random()*20).toInt()) {
}