package dto;

import java.time.LocalDateTime;

public class RoomRentRequestDTO {

	private int idHotel;
    private int idRoom;
    private int cantidad;
    private LocalDateTime startRent;
    private LocalDateTime endRent;
    
    public RoomRentRequestDTO() {}

	public RoomRentRequestDTO(int idHotel, int idRoom, int cantidad, LocalDateTime startRent, LocalDateTime endRent) {
		super();
		this.idHotel = idHotel;
		this.idRoom = idRoom;
		this.cantidad = cantidad;
		this.startRent = startRent;
		this.endRent = endRent;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public int getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LocalDateTime getStartRent() {
		return startRent;
	}

	public void setStartRent(LocalDateTime startRent) {
		this.startRent = startRent;
	}

	public LocalDateTime getEndRent() {
		return endRent;
	}

	public void setEndRent(LocalDateTime endRent) {
		this.endRent = endRent;
	}
    
    
}
