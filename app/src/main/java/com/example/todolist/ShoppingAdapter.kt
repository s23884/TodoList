import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.ShoppingItem
import kotlinx.android.synthetic.main.activity_shoping_item.view.*
import kotlinx.android.synthetic.main.item_shopping.view.*


class ShoppingAdapter(
    private val shoppingItems: MutableList<ShoppingItem>
) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_shopping,
                parent,
                false
            )
        )
    }

    fun addShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        notifyItemInserted(shoppingItems.size - 1)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = shoppingItems[position]
        holder.itemView.apply {
            tvShoppingItem.text = currentItem.name
            cbPurchased.isChecked = currentItem.isPurchased
            if (currentItem.isPurchased) {
                tvShoppingItem.paintFlags = tvShoppingItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                tvShoppingItem.paintFlags = tvShoppingItem.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
            cbPurchased.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    tvShoppingItem.paintFlags = tvShoppingItem.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    tvShoppingItem.paintFlags = tvShoppingItem.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                }
                currentItem.isPurchased = isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return shoppingItems.size
    }
}
