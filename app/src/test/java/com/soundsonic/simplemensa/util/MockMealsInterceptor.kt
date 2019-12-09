package com.soundsonic.simplemensa.util

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class MockMealsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(json)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                json.toByteArray())
            )
            .addHeader("content-type", "application/json")
            .build()
    }

    companion object {
        private const val json = """[
    {
        "id": 238887,
        "name": "Möhrencremesuppe",
        "notes": [
            "Menü ist vegan",
            "Soja (F)"
        ],
        "prices": {
            "Studierende": 1.74,
            "Bedienstete": 3.54
        },
        "category": "Suppe",
        "image": "https://static.studentenwerk-dresden.de/bilder/mensen/studentenwerk-dresden-lieber-mensen-gehen.jpg",
        "url": "https://www.studentenwerk-dresden.de/mensen/speiseplan/details-238887.html"
    },
    {
        "id": 238142,
        "name": "Gebratene Gnocchi in Spinat-Käsesoße, dazu Tomatensalat",
        "notes": [
            "Menü ist vegetarisch",
            "mit Farbstoff (1)",
            "Glutenhaltiges Getreide (A)",
            "Weizen (A1)",
            "Eier (C)",
            "Soja (F)",
            "Milch/Milchzucker (Laktose) (G)"
        ],
        "prices": {
            "Studierende": 2.73,
            "Bedienstete": 4.53
        },
        "category": "Fit & Vital 2",
        "image": "https://bilderspeiseplan.studentenwerk-dresden.de/m35/201912/238142.jpg",
        "url": "https://www.studentenwerk-dresden.de/mensen/speiseplan/details-238142.html"
    },
    {
        "id": 238145,
        "name": "Hähnchenbrustfilet \"Napoli\" auf Tomatenspaghetti",
        "notes": [
            "enthält Knoblauch",
            "Glutenhaltiges Getreide (A)",
            "Weizen (A1)"
        ],
        "prices": {
            "Studierende": 3.19,
            "Bedienstete": 4.99
        },
        "category": "Aktionstheke",
        "image": "https://bilderspeiseplan.studentenwerk-dresden.de/m35/201912/238145.jpg",
        "url": "https://www.studentenwerk-dresden.de/mensen/speiseplan/details-238145.html"
    },
    {
        "id": 238179,
        "name": "Lauch-Hackfleisch-Käsesoße",
        "notes": [
            "enthält Schweinefleisch",
            "Glutenhaltiges Getreide (A)",
            "Weizen (A1)",
            "Milch/Milchzucker (Laktose) (G)"
        ],
        "prices": [],
        "category": "Pasta",
        "image": "https://static.studentenwerk-dresden.de/bilder/mensen/studentenwerk-dresden-lieber-mensen-gehen.jpg",
        "url": "https://www.studentenwerk-dresden.de/mensen/speiseplan/details-238179.html"
    },
    {
        "id": 238286,
        "name": "Fruchtige Tomatensoße",
        "notes": [
            "Menü ist vegan"
        ],
        "prices": [],
        "category": "Pasta",
        "image": "https://static.studentenwerk-dresden.de/bilder/mensen/studentenwerk-dresden-lieber-mensen-gehen.jpg",
        "url": "https://www.studentenwerk-dresden.de/mensen/speiseplan/details-238286.html"
    },
    {
        "id": 238911,
        "name": "Zucchini-Pilzsuppe mit getrockneten Tomaten",
        "notes": [
            "Menü ist vegetarisch",
            "enthält Knoblauch",
            "mit Antioxydationsmittel (3)",
            "geschwefelt (5)",
            "Glutenhaltiges Getreide (A)",
            "Weizen (A1)",
            "Milch/Milchzucker (Laktose) (G)",
            "Sulfit/Schwefeldioxid (L)"
        ],
        "prices": {
            "Studierende": 1.74,
            "Bedienstete": 3.54
        },
        "category": "Suppe",
        "image": "https://static.studentenwerk-dresden.de/bilder/mensen/studentenwerk-dresden-lieber-mensen-gehen.jpg",
        "url": "https://www.studentenwerk-dresden.de/mensen/speiseplan/details-238911.html"
    },
    {
        "id": 238287,
        "name": "Pasta mit Basilikumpesto",
        "notes": [
            "Menü ist vegan"
        ],
        "prices": [],
        "category": "Pasta",
        "image": "https://static.studentenwerk-dresden.de/bilder/mensen/studentenwerk-dresden-lieber-mensen-gehen.jpg",
        "url": "https://www.studentenwerk-dresden.de/mensen/speiseplan/details-238287.html"
    }
]"""
    }
}
