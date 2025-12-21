package pl.wsb.fitnesstracker.training.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.util.Date;

/**
 * Data Transfer Object for Training entity.
 * Contains full training details including associated user information.
 */
public class TrainingDto {

    private Long id;
    private UserDto user;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00", timezone = "UTC")
    private Date startTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS+00:00", timezone = "UTC")
    private Date endTime;
    
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;

    /**
     * Default constructor for JSON deserialization.
     */
    public TrainingDto() {
    }

    /**
     * Creates a new TrainingDto with all fields.
     * 
     * @param id the training ID
     * @param user the associated user DTO
     * @param startTime training start time
     * @param endTime training end time
     * @param activityType type of activity
     * @param distance distance covered in kilometers
     * @param averageSpeed average speed in km/h
     */
    public TrainingDto(Long id, UserDto user, Date startTime, Date endTime, 
                      ActivityType activityType, double distance, double averageSpeed) {
        this.id = id;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }

    /**
     * Gets the training ID.
     * @return training ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the training ID.
     * @param id training ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the associated user.
     * @return user DTO
     */
    public UserDto getUser() {
        return user;
    }

    /**
     * Sets the associated user.
     * @param user user DTO
     */
    public void setUser(UserDto user) {
        this.user = user;
    }

    /**
     * Gets the training start time.
     * @return start time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets the training start time.
     * @param startTime start time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the training end time.
     * @return end time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Sets the training end time.
     * @param endTime end time
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the activity type.
     * @return activity type
     */
    public ActivityType getActivityType() {
        return activityType;
    }

    /**
     * Sets the activity type.
     * @param activityType activity type
     */
    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    /**
     * Gets the distance covered.
     * @return distance in kilometers
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the distance covered.
     * @param distance distance in kilometers
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Gets the average speed.
     * @return average speed in km/h
     */
    public double getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * Sets the average speed.
     * @param averageSpeed average speed in km/h
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }
}
