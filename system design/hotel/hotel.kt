data class Hotel(
    val rooms: List<Room>
)

data class Room(
    val id: Int, val name: String, val type: RoomType, val location: Location, val description: String
)

data class Location(
    val building: Int, val floor: Int, val number: Int
)

data class City(
    val hotels: List<Hotel>
)

data class Order(
    val date: LocalDate,
    val roomId: Int,
    val price: BigDecimal,
    val status: OrderStatus
)

enum class OrderStatus {
    PROCESSING, BOOKED, COMPLETED
}

data class RoomSchedule(
    val id: String,
    val date: LocalDate,
    val roomId: Int,
    val price: BigDecimal,
    val status: RoomStatus
)

data class SearchResult(
    val type: RoomType,
    val rooms: List<RoomSchedule>
)

enum class RoomStatus {
    AVAILABLE, UNAVAILABLE
}

enum class RoomType {
    SMALL, MEDIUM, LARGE
}

class HotelService {
    fun search(startDate: LocalDate, endDate, LocalDate): SearchResult {

    }

    fun book(roomType: RoomType, startDate: LocalDate, endDate, LocalDate): Order {

    }

    fun pay(order: Order) {
        order.status = OrderStatus.BOOKED
    }
}