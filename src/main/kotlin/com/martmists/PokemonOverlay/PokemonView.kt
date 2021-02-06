package com.martmists.PokemonOverlay

import javafx.event.EventHandler
import javafx.event.ActionEvent
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import org.json.JSONArray
import org.json.JSONObject
import tornadofx.*
import java.io.File
import java.lang.Thread.sleep

class PokemonView : View() {
    private val empty = Pokemon(0)
    private val cache = mutableMapOf<String, Image>()
    private val placeholder = getImage("http://via.placeholder.com/0x0/f4f4f4/f4f4f4")

    private fun getImage(url: String): Image {
        return if (cache.containsKey(url)) {
            cache.getValue(url)
        } else {
            val img = Image(Http.get(url).body()!!.byteStream())
            cache[url] = img
            img
        }
    }

    override val root = hbox(5) {
        addClass(PokemonStyle.App)
        vbox(15) {
            addClass(PokemonStyle.Column)
            borderpane {
                addClass(PokemonStyle.Pokemon)

                center {
                    pane {
                        addClass(PokemonStyle.Inner)
                        imageview(getImage(empty.icon)).apply {
                            layoutX = -17.0
                            layoutY = -17.0
                            addClass(PokemonStyle.PokemonIcon)
                        }
                        label {
                            text = empty.nickname
                            layoutX = 64.0
                            layoutY = 5.0
                            addClass(PokemonStyle.Nickname)
                        }
                        imageview().apply {
                            layoutX = 65.0
                            layoutY = 40.0
                            addClass(PokemonStyle.ItemIcon)
                        }
                        label {
                            text = empty.item
                            layoutX = 67.0
                            layoutY = 37.0
                            addClass(PokemonStyle.ItemName)
                        }
                        imageview(getImage(empty.ball)) {
                            layoutX = 34.0
                            layoutY = 34.0
                            addClass(PokemonStyle.PokeBall)
                        }
                        imageview(placeholder).apply {
                            layoutX = 64.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type1)
                        }
                        imageview(placeholder).apply {
                            layoutX = 114.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type2)
                        }
                        label {
                            text = String.format("%-9s\t%d", "HP: ", empty.hp)
                            layoutX = 155.0
                            layoutY = 0.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Attack: ", empty.attack)
                            layoutX = 155.0
                            layoutY = 8.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Defense: ", empty.defense)
                            layoutX = 155.0
                            layoutY = 16.0
                            addClass(PokemonStyle.Stat)

                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Atk: ", empty.spAttack)
                            layoutX = 155.0
                            layoutY = 24.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Def: ", empty.spDefense)
                            layoutX = 155.0
                            layoutY = 32.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Speed: ", empty.speed)
                            layoutX = 155.0
                            layoutY = 40.0
                            addClass(PokemonStyle.Stat)
                        }
                        if (readJsonPath.isNullOrBlank()) {
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "X"
                                addClass(PokemonStyle.XButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "+"
                                addClass(PokemonStyle.PlusButton)
                            }
                            button {
                                layoutX = 310.0
                                layoutY = 25.0
                                text = "+"
                                addClass(PokemonStyle.LevelPlusButton)
                            }
                            button {
                                layoutX = 340.0
                                layoutY = 25.0
                                text = "-"
                                addClass(PokemonStyle.LevelMinusButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 26.0
                                text = "\u270e"
                                addClass(PokemonStyle.EditButton)
                            }
                        }
                    }
                }
            }
            borderpane {
                addClass(PokemonStyle.Pokemon)

                center {
                    pane {
                        addClass(PokemonStyle.Inner)
                        imageview(getImage(empty.icon)).apply {
                            layoutX = -17.0
                            layoutY = -17.0
                            addClass(PokemonStyle.PokemonIcon)
                        }
                        label {
                            text = empty.nickname
                            layoutX = 64.0
                            layoutY = 5.0
                            addClass(PokemonStyle.Nickname)
                        }
                        imageview().apply {
                            layoutX = 65.0
                            layoutY = 40.0
                            addClass(PokemonStyle.ItemIcon)
                        }
                        label {
                            text = empty.item
                            layoutX = 67.0
                            layoutY = 37.0
                            addClass(PokemonStyle.ItemName)
                        }
                        imageview(getImage(empty.ball)) {
                            layoutX = 34.0
                            layoutY = 34.0
                            addClass(PokemonStyle.PokeBall)
                        }
                        imageview(placeholder).apply {
                            layoutX = 64.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type1)
                        }
                        imageview(placeholder).apply {
                            layoutX = 114.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type2)
                        }
                        label {
                            text = String.format("%-9s\t%d", "HP: ", empty.hp)
                            layoutX = 155.0
                            layoutY = 0.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Attack: ", empty.attack)
                            layoutX = 155.0
                            layoutY = 8.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Defense: ", empty.defense)
                            layoutX = 155.0
                            layoutY = 16.0
                            addClass(PokemonStyle.Stat)

                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Atk: ", empty.spAttack)
                            layoutX = 155.0
                            layoutY = 24.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Def: ", empty.spDefense)
                            layoutX = 155.0
                            layoutY = 32.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Speed: ", empty.speed)
                            layoutX = 155.0
                            layoutY = 40.0
                            addClass(PokemonStyle.Stat)
                        }
                        if (readJsonPath.isNullOrBlank()) {
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "X"
                                addClass(PokemonStyle.XButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "+"
                                addClass(PokemonStyle.PlusButton)
                            }
                            button {
                                layoutX = 310.0
                                layoutY = 25.0
                                text = "+"
                                addClass(PokemonStyle.LevelPlusButton)
                            }
                            button {
                                layoutX = 340.0
                                layoutY = 25.0
                                text = "-"
                                addClass(PokemonStyle.LevelMinusButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 26.0
                                text = "\u270e"
                                addClass(PokemonStyle.EditButton)
                            }
                        }
                    }
                }
            }
        }
        vbox(15) {
            addClass(PokemonStyle.Column)
            borderpane {
                addClass(PokemonStyle.Pokemon)

                center {
                    pane {
                        addClass(PokemonStyle.Inner)
                        imageview(getImage(empty.icon)).apply {
                            layoutX = -17.0
                            layoutY = -17.0
                            addClass(PokemonStyle.PokemonIcon)
                        }
                        label {
                            text = empty.nickname
                            layoutX = 64.0
                            layoutY = 5.0
                            addClass(PokemonStyle.Nickname)
                        }
                        imageview().apply {
                            layoutX = 65.0
                            layoutY = 40.0
                            addClass(PokemonStyle.ItemIcon)
                        }
                        label {
                            text = empty.item
                            layoutX = 67.0
                            layoutY = 37.0
                            addClass(PokemonStyle.ItemName)
                        }
                        imageview(getImage(empty.ball)) {
                            layoutX = 34.0
                            layoutY = 34.0
                            addClass(PokemonStyle.PokeBall)
                        }
                        imageview(placeholder).apply {
                            layoutX = 64.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type1)
                        }
                        imageview(placeholder).apply {
                            layoutX = 114.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type2)
                        }
                        label {
                            text = String.format("%-9s\t%d", "HP: ", empty.hp)
                            layoutX = 155.0
                            layoutY = 0.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Attack: ", empty.attack)
                            layoutX = 155.0
                            layoutY = 8.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Defense: ", empty.defense)
                            layoutX = 155.0
                            layoutY = 16.0
                            addClass(PokemonStyle.Stat)

                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Atk: ", empty.spAttack)
                            layoutX = 155.0
                            layoutY = 24.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Def: ", empty.spDefense)
                            layoutX = 155.0
                            layoutY = 32.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Speed: ", empty.speed)
                            layoutX = 155.0
                            layoutY = 40.0
                            addClass(PokemonStyle.Stat)
                        }
                        if (readJsonPath.isNullOrBlank()) {
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "X"
                                addClass(PokemonStyle.XButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "+"
                                addClass(PokemonStyle.PlusButton)
                            }
                            button {
                                layoutX = 310.0
                                layoutY = 25.0
                                text = "+"
                                addClass(PokemonStyle.LevelPlusButton)
                            }
                            button {
                                layoutX = 340.0
                                layoutY = 25.0
                                text = "-"
                                addClass(PokemonStyle.LevelMinusButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 26.0
                                text = "\u270e"
                                addClass(PokemonStyle.EditButton)
                            }
                        }
                    }
                }
            }
            borderpane {
                addClass(PokemonStyle.Pokemon)

                center {
                    pane {
                        addClass(PokemonStyle.Inner)
                        imageview(getImage(empty.icon)).apply {
                            layoutX = -17.0
                            layoutY = -17.0
                            addClass(PokemonStyle.PokemonIcon)
                        }
                        label {
                            text = empty.nickname
                            layoutX = 64.0
                            layoutY = 5.0
                            addClass(PokemonStyle.Nickname)
                        }
                        imageview().apply {
                            layoutX = 65.0
                            layoutY = 40.0
                            addClass(PokemonStyle.ItemIcon)
                        }
                        label {
                            text = empty.item
                            layoutX = 70.0
                            layoutY = 37.0
                            addClass(PokemonStyle.ItemName)
                        }
                        imageview(getImage(empty.ball)) {
                            layoutX = 34.0
                            layoutY = 34.0
                            addClass(PokemonStyle.PokeBall)
                        }
                        imageview(placeholder).apply {
                            layoutX = 64.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type1)
                        }
                        imageview(placeholder).apply {
                            layoutX = 114.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type2)
                        }
                        label {
                            text = String.format("%-9s\t%d", "HP: ", empty.hp)
                            layoutX = 155.0
                            layoutY = 0.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Attack: ", empty.attack)
                            layoutX = 155.0
                            layoutY = 8.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Defense: ", empty.defense)
                            layoutX = 155.0
                            layoutY = 16.0
                            addClass(PokemonStyle.Stat)

                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Atk: ", empty.spAttack)
                            layoutX = 155.0
                            layoutY = 24.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Def: ", empty.spDefense)
                            layoutX = 155.0
                            layoutY = 32.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Speed: ", empty.speed)
                            layoutX = 155.0
                            layoutY = 40.0
                            addClass(PokemonStyle.Stat)
                        }
                        if (readJsonPath.isNullOrBlank()) {
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "X"
                                addClass(PokemonStyle.XButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "+"
                                addClass(PokemonStyle.PlusButton)
                            }
                            button {
                                layoutX = 310.0
                                layoutY = 25.0
                                text = "+"
                                addClass(PokemonStyle.LevelPlusButton)
                            }
                            button {
                                layoutX = 340.0
                                layoutY = 25.0
                                text = "-"
                                addClass(PokemonStyle.LevelMinusButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 26.0
                                text = "\u270e"
                                addClass(PokemonStyle.EditButton)
                            }
                        }
                    }
                }
            }
        }
        vbox(15) {
            addClass(PokemonStyle.Column)
            borderpane {
                addClass(PokemonStyle.Pokemon)

                center {
                    pane {
                        addClass(PokemonStyle.Inner)
                        imageview(getImage(empty.icon)).apply {
                            layoutX = -17.0
                            layoutY = -17.0
                            addClass(PokemonStyle.PokemonIcon)
                        }
                        label {
                            text = empty.nickname
                            layoutX = 64.0
                            layoutY = 5.0
                            addClass(PokemonStyle.Nickname)
                        }
                        imageview().apply {
                            layoutX = 65.0
                            layoutY = 40.0
                            addClass(PokemonStyle.ItemIcon)
                        }
                        label {
                            text = empty.item
                            layoutX = 70.0
                            layoutY = 37.0
                            addClass(PokemonStyle.ItemName)
                        }
                        imageview(getImage(empty.ball)) {
                            layoutX = 34.0
                            layoutY = 34.0
                            addClass(PokemonStyle.PokeBall)
                        }
                        imageview(placeholder).apply {
                            layoutX = 64.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type1)
                        }
                        imageview(placeholder).apply {
                            layoutX = 114.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type2)
                        }
                        label {
                            text = String.format("%-9s\t%d", "HP: ", empty.hp)
                            layoutX = 155.0
                            layoutY = 0.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Attack: ", empty.attack)
                            layoutX = 155.0
                            layoutY = 8.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Defense: ", empty.defense)
                            layoutX = 155.0
                            layoutY = 16.0
                            addClass(PokemonStyle.Stat)

                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Atk: ", empty.spAttack)
                            layoutX = 155.0
                            layoutY = 24.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Def: ", empty.spDefense)
                            layoutX = 155.0
                            layoutY = 32.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Speed: ", empty.speed)
                            layoutX = 155.0
                            layoutY = 40.0
                            addClass(PokemonStyle.Stat)
                        }
                        if (readJsonPath.isNullOrBlank()) {
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "X"
                                addClass(PokemonStyle.XButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "+"
                                addClass(PokemonStyle.PlusButton)
                            }
                            button {
                                layoutX = 310.0
                                layoutY = 25.0
                                text = "+"
                                addClass(PokemonStyle.LevelPlusButton)
                            }
                            button {
                                layoutX = 340.0
                                layoutY = 25.0
                                text = "-"
                                addClass(PokemonStyle.LevelMinusButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 26.0
                                text = "\u270e"
                                addClass(PokemonStyle.EditButton)
                            }
                        }
                    }
                }
            }
            borderpane {
                addClass(PokemonStyle.Pokemon)

                center {
                    pane {
                        addClass(PokemonStyle.Inner)
                        imageview(getImage(empty.icon)).apply {
                            layoutX = -17.0
                            layoutY = -17.0
                            addClass(PokemonStyle.PokemonIcon)
                        }
                        label {
                            text = empty.nickname
                            layoutX = 64.0
                            layoutY = 5.0
                            addClass(PokemonStyle.Nickname)
                        }
                        imageview().apply {
                            layoutX = 65.0
                            layoutY = 40.0
                            addClass(PokemonStyle.ItemIcon)
                        }
                        label {
                            text = empty.item
                            layoutX = 70.0
                            layoutY = 37.0
                            addClass(PokemonStyle.ItemName)
                        }
                        imageview(getImage(empty.ball)) {
                            layoutX = 34.0
                            layoutY = 34.0
                            addClass(PokemonStyle.PokeBall)
                        }
                        imageview(placeholder).apply {
                            layoutX = 64.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type1)
                        }
                        imageview(placeholder).apply {
                            layoutX = 114.0
                            layoutY = 21.0
                            addClass(PokemonStyle.Type2)
                        }
                        label {
                            text = String.format("%-9s\t%d", "HP: ", empty.hp)
                            layoutX = 155.0
                            layoutY = 0.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Attack: ", empty.attack)
                            layoutX = 155.0
                            layoutY = 8.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Defense: ", empty.defense)
                            layoutX = 155.0
                            layoutY = 16.0
                            addClass(PokemonStyle.Stat)

                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Atk: ", empty.spAttack)
                            layoutX = 155.0
                            layoutY = 24.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Sp. Def: ", empty.spDefense)
                            layoutX = 155.0
                            layoutY = 32.0
                            addClass(PokemonStyle.Stat)
                        }
                        label {
                            text = String.format("%-9s\t%d", "Speed: ", empty.speed)
                            layoutX = 155.0
                            layoutY = 40.0
                            addClass(PokemonStyle.Stat)
                        }
                        if (readJsonPath.isNullOrBlank()) {
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "X"
                                addClass(PokemonStyle.XButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 4.0
                                text = "+"
                                addClass(PokemonStyle.PlusButton)
                            }
                            button {
                                layoutX = 310.0
                                layoutY = 25.0
                                text = "+"
                                addClass(PokemonStyle.LevelPlusButton)
                            }
                            button {
                                layoutX = 340.0
                                layoutY = 25.0
                                text = "-"
                                addClass(PokemonStyle.LevelMinusButton)
                            }
                            button {
                                layoutX = 370.0
                                layoutY = 26.0
                                text = "\u270e"
                                addClass(PokemonStyle.EditButton)
                            }
                        }
                    }
                }
            }
        }
    }

    private val panes = mutableListOf<BorderPane>()
    private val pokes = mutableListOf(empty, empty, empty, empty, empty, empty)

    init {
        root.getChildList()!!.forEachIndexed { i, vbox ->
            vbox.getChildList()!!.forEachIndexed { j, it ->
                it.addClass("row${j + 1}")
                it as BorderPane
                val children = it.center.getChildList()!!

                if (readJsonPath.isNullOrBlank()) {
                    val remove = children[13] as Button
                    val add = children[14] as Button
                    val buttonLevelPlus = children[15] as Button
                    val buttonLevelMinus = children[16] as Button
                    val buttonEdit = children[17] as Button


                    remove.onAction = EventHandler<ActionEvent> { _ -> removePokemon(i * 2 + j) }
                    add.onAction = EventHandler<ActionEvent> { _ -> menuPokemon(i * 2 + j) }

                    buttonLevelPlus.onAction = EventHandler<ActionEvent> { _ ->
                        pokes[i * 2 + j].level += 1
                        update(i * 2 + j)
                    }
                    buttonLevelMinus.onAction = EventHandler<ActionEvent> { _ ->
                        pokes[i * 2 + j].level -= 1
                        update(i * 2 + j)
                    }
                    buttonEdit.onAction = EventHandler<ActionEvent> { menuPokemon(i * 2 + j, true) }
                }
                panes.add(it)
            }
        }

        if (!readJsonPath.isNullOrBlank()) {
            Thread {
                while (true) {
                    sleep(1000L)
                    val text = File(readJsonPath).readText()
                    try {
                        val json = JSONArray(text)

                        for (i in 0 until json.length()) {
                            runAsync {
                                val pokemonJson = json.getJSONObject(i)!!
                                var pid = pokemonJson.getInt("pokemonID")
                                val pok = if (pid == -1) {
                                    pid = 0
                                    Pokemon(pid)
                                } else {
                                    Pokemon(
                                            pid
                                    ).apply {
                                        setPLevel(pokemonJson.getInt("level"))
                                        if (pokemonJson.getString("helditemtext") != "none") {
                                            setPItem(pokemonJson.getString("helditemtext"))
                                        }
                                        if (pokemonJson.getInt("maxhpstat") != 0) {
                                            setPHp(pokemonJson.getInt("maxhpstat"))
                                        }
                                        if (pokemonJson.getInt("atkstat") != 0) {
                                            setPAttack(pokemonJson.getInt("atkstat"))
                                        }
                                        if (pokemonJson.getInt("defstat") != 0) {
                                            setPDefense(pokemonJson.getInt("defstat"))
                                        }
                                        if (pokemonJson.getInt("spastat") != 0) {
                                            setPSpAttack(pokemonJson.getInt("spastat"))
                                        }
                                        if (pokemonJson.getInt("spdstat") != 0) {
                                            setPSpDefense(pokemonJson.getInt("spdstat"))
                                        }
                                        if (pokemonJson.getInt("spestat") != 0) {
                                            setPSpeed(pokemonJson.getInt("spestat"))
                                        }
                                        if (pokemonJson.getInt("hpev") != 0) {
                                            setPHpEv(pokemonJson.getInt("hpev"))
                                        }
                                        if (pokemonJson.getInt("atkev") != 0) {
                                            setPAttackEv(pokemonJson.getInt("atkev"))
                                        }
                                        if (pokemonJson.getInt("defev") != 0) {
                                            setPDefenseEv(pokemonJson.getInt("defev"))
                                        }
                                        if (pokemonJson.getInt("spaev") != 0) {
                                            setPSpAttackEv(pokemonJson.getInt("spaev"))
                                        }
                                        if (pokemonJson.getInt("spdev") != 0 ) {
                                            setPSpDefenseEv(pokemonJson.getInt("spdev"))
                                        }
                                        if (pokemonJson.getInt("speev") != 0) {
                                            setPSpeedEv(pokemonJson.getInt("speev"))
                                        }
                                        if (pokemonJson.getInt("hpiv") != 0) {
                                            setPHpIv(pokemonJson.getInt("hpiv"))
                                        }
                                        if (pokemonJson.getInt("atkiv") != 0) {
                                            setPAttackIv(pokemonJson.getInt("atkiv"))
                                        }
                                        if (pokemonJson.getInt("defiv") != 0) {
                                            setPDefenseIv(pokemonJson.getInt("defiv"))
                                        }
                                        if (pokemonJson.getInt("spaiv") != 0) {
                                            setPSpAttackIv(pokemonJson.getInt("spaiv"))
                                        }
                                        if (pokemonJson.getInt("spdiv") != 0 ) {
                                            setPSpDefenseIv(pokemonJson.getInt("spdiv"))
                                        }
                                        if (pokemonJson.getInt("speiv") != 0) {
                                            setPSpeedIv(pokemonJson.getInt("speiv"))
                                        }
                                    }
                                }
                                pokes[i] = pok
                            } ui {
                                update(i)
                            }
                        }
                    } catch (e: Exception) {
                        continue
                    }
                }
            }.start()
        }
    }

    private fun update(i: Int) {
        val pokemon = pokes[i]
        val pane = panes[i]
        val children = pane.center.getChildList()!!

        val pokeicon = children[0] as ImageView
        val ballicon = children[4] as ImageView
        val itemicon = children[2] as ImageView
        val nickname = children[1] as Label
        val itemname = children[3] as Label
        val hp = children[7] as Label
        val attack = children[8] as Label
        val defense = children[9] as Label
        val spAttack = children[10] as Label
        val spDefense = children[11] as Label
        val speed = children[12] as Label

        val typeImage1 = children[5] as ImageView
        val typeImage2 = children[6] as ImageView

        pokeicon.image = getImage(pokemon.icon)
        ballicon.image = getImage(pokemon.ball)
        nickname.text = if (pokemon.nickname != "") "${pokemon.nickname} Lvl. ${pokemon.level}" else ""
        itemname.text = pokemon.item
        hp.text =           String.format("%-10s\t%-4d\t%-5s\t%-4d\t%-5s\t%-4d", "HP:", pokemon.hp, "EVs:", pokemon.hpEv, "IVs:", pokemon.hpIv)
        attack.text =       String.format("%-10s\t%-4d\t%-5s\t%-4d\t%-5s\t%-4d", "Attack:", pokemon.attack, "EVs:", pokemon.attackEv,"IVs:", pokemon.attackIv)
        defense.text =      String.format("%-10s\t%-4d\t%-5s\t%-4d\t%-5s\t%-4d", "Defense:", pokemon.defense, "EVs:", pokemon.defenseEv, "IVs:", pokemon.defenseIv)
        spAttack.text =     String.format("%-10s\t%-4d\t%-5s\t%-4d\t%-5s\t%-4d", "Sp. Atk:", pokemon.spAttack, "EVs:", pokemon.spAttackEv, "IVs:", pokemon.spAttackIv)
        spDefense.text =    String.format("%-10s\t%-4d\t%-5s\t%-4d\t%-5s\t%-4d", "Sp. Def:", pokemon.spDefense, "EVs:", pokemon.spDefenseEv, "IVs:", pokemon.spDefenseIv)
        speed.text =        String.format("%-10s\t%-4d\t%-5s\t%-4d\t%-5s\t%-4d", "Speed:", pokemon.speed, "EVs:", pokemon.speedEv, "IVs:", pokemon.speedIv)

        itemicon.image = if (pokemon.item == "") placeholder else getImage("https://cdn.bulbagarden.net/upload/d/d6/Held_icon_VII.png")

        typeImage1.image = getImage(pokemon.types[0])
        typeImage2.image = getImage(pokemon.types[1])
        if (readJsonPath.isNullOrBlank()){
            val buttonX = children[13] as Button
            val buttonPlus = children[14] as Button

            val buttonLevelPlus = children[15] as Button
            val buttonLevelMinus = children[16] as Button

            val buttonEdit = children[17] as Button

            buttonX.isVisible = (pokemon.nickname != "")
            buttonPlus.isVisible = if (i > 0) panes.subList(0, i).filter { !it.center.getChildList()!![13].isVisible }.isEmpty() and !children[13].isVisible else pokemon == empty

            buttonLevelPlus.isVisible = buttonX.isVisible
            buttonLevelPlus.isDisable = (pokemon.level == 100)
            buttonLevelMinus.isVisible = buttonX.isVisible
            buttonLevelMinus.isDisable = (pokemon.level == 1)

            buttonEdit.isVisible = buttonX.isVisible
        }
    }

    fun setPokemon(pos: Int, pokemon: Pokemon) {
        try {
            pokes.removeAt(pos)
        } catch (e: Exception) {
        }
        pokes.add(pos, pokemon)
        update(pos)
        if (pos < 5)
            update(pos + 1)
    }

    private fun menuPokemon(pos: Int, edit: Boolean = false) {
        val editScope = Scope()
        val model = if (edit) EditPokemon(pos, this, pokes[pos]) else AddPokemon(pos, this)
        setInScope(model, editScope)
        find(model::class, editScope).openWindow()
    }

    private fun removePokemon(pos: Int) {
        pokes.removeAt(pos)
        pokes.add(empty)
        for (i in pos until pokes.indexOfFirst { it == empty } + 2)
            if (i < 6)
                update(i)
    }

    companion object {
        var readJsonPath: String? = null
    }
}