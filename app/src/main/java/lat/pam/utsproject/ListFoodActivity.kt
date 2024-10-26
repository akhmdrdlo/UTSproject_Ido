package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Menyiapkan data makanan
        foodList = listOf(
            Food("Batagor Mang Kujo", "Batagor asli enak dari Bandung", R.drawable.batagor),
            Food("Black Salad", "Salad segar yang dibuat secara langsung", R.drawable.black_salad),
            Food("CheeseCake Mlewah", "Kue keju lembut dan lezat dengan lapisan biskuit renyah", R.drawable.cheesecake),
            Food("Cireng Mang Bonin", "Camilan khas Sunda yang renyah dan gurih, terbuat dari tepung kanji", R.drawable.cireng),
            Food("Donat Mama Rido", "Donat lembut dengan berbagai macam topping yang menggugah selera", R.drawable.donut),
            Food("Nasi Goreng Spesial", "Nasi goreng dengan campuran daging, sayuran, dan bumbu spesial yang kaya rasa", R.drawable.nasigoreng),
            Food("Eleph Cappucino", "Kopi cappucino asli yang dibuat dari Kopi Arabica", R.drawable.cappuchino)
        )

        adapter = FoodAdapter(foodList, this::onFoodItemClick) // Pass the click listener function
        recyclerView.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val orderButton = findViewById<ImageButton>(R.id.btnOrder) // Assuming you have an order button

        orderButton.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onFoodItemClick(food: Food) {
        val intent = Intent(this, OrderActivity::class.java)
        intent.putExtra("foodName", food.name)
        startActivity(intent)
    }
}