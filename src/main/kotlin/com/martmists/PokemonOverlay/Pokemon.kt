package com.martmists.PokemonOverlay

import org.json.JSONObject

val pokemonDB = JSONObject(ClassLoader.getSystemResource("out.json").readText())
val ballsDB = JSONObject(ClassLoader.getSystemResource("balls.json").readText())
val typesDB = JSONObject(ClassLoader.getSystemResource("types.json").readText())


class Pokemon(id: Any) {
    var level = 1
    var name = ""
    var nickname = ""
    var icon = ""
    var dexno = ""
    var types = mutableListOf<String>()
    var ball = "https://cdn.bulbagarden.net/upload/9/93/Bag_Pok%C3%A9_Ball_Sprite.png"
    var item = ""
    var hp = 0
    var attack = 0
    var defense = 0
    var spAttack = 0
    var spDefense = 0
    var speed = 0
    var hpEv = 0
    var attackEv = 0
    var defenseEv = 0
    var spAttackEv = 0
    var spDefenseEv = 0
    var speedEv = 0
    var hpIv = 0
    var attackIv = 0
    var defenseIv = 0
    var spAttackIv = 0
    var spDefenseIv = 0
    var speedIv = 0

    init {
        if (id != 0) {
            dexno = id.toString().padStart(3, '0')

            val json = pokemonDB.getJSONObject(dexno)

            dexno = json.getString("dexno")

            for (type in json.getJSONArray("types")) {
                types.add(typesDB.getString(type.toString()))
            }

            if (types.size == 1){
                types.add("http://via.placeholder.com/0x0/f4f4f4/f4f4f4")
            }

            name = json.getString("name")
            nickname = name
            icon = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + json.getString("dexno") + ".png"
        } else {
            icon = "http://via.placeholder.com/0x0/f4f4f4/f4f4f4"
            ball = "http://via.placeholder.com/0x0/f4f4f4/f4f4f4"
            types.add("http://via.placeholder.com/0x0/f4f4f4/f4f4f4")
            types.add("http://via.placeholder.com/0x0/f4f4f4/f4f4f4")
        }
    }

    fun setPNick(name: String){
        nickname = name
    }

    fun setPLevel(level: Int){
        this.level = level
    }

    fun setPBall(ball: String){
        this.ball = ballsDB.getString(ball)
    }

    fun setPItem(name: String){
        this.item = name
    }

    fun setPHp(hp: Int){
        this.hp = hp
    }

    fun setPAttack(attack: Int){
        this.attack = attack
    }

    fun setPDefense(defense: Int){
        this.defense = defense
    }

    fun setPSpAttack(spAttack: Int){
        this.spAttack = spAttack
    }

    fun setPSpDefense(spDefense: Int){
        this.spDefense = spDefense
    }

    fun setPSpeed(speed: Int){
        this.speed = speed
    }
    fun setPHpEv(hpEv: Int){
        this.hpEv = hpEv
    }

    fun setPAttackEv(attackEv: Int){
        this.attackEv = attackEv
    }

    fun setPDefenseEv(defenseEv: Int){
        this.defenseEv = defenseEv
    }

    fun setPSpAttackEv(spAttackEv: Int){
        this.spAttackEv = spAttackEv
    }

    fun setPSpDefenseEv(spDefenseEv: Int){
        this.spDefenseEv = spDefenseEv
    }

    fun setPSpeedEv(speedEv: Int){
        this.speedEv = speedEv
    }
    fun setPHpIv(hpIv: Int){
        this.hpIv = hpIv
    }

    fun setPAttackIv(attackIv: Int){
        this.attackIv = attackIv
    }

    fun setPDefenseIv(defenseIv: Int){
        this.defenseIv = defenseIv
    }

    fun setPSpAttackIv(spAttackIv: Int){
        this.spAttackIv = spAttackIv
    }

    fun setPSpDefenseIv(spDefenseIv: Int){
        this.spDefenseIv = spDefenseIv
    }

    fun setPSpeedIv(speedIv: Int){
        this.speedIv = speedIv
    }
}


fun getAllPokemon(): Array<Pokemon>{
    val allPokemon = mutableListOf<Pokemon>()

    for (key in pokemonDB.keys()){
        allPokemon.add(Pokemon(key))
    }

    return allPokemon.toTypedArray()
}

fun getAllBalls(): List<String>{
    return ballsDB.keys().asSequence().toList()
}