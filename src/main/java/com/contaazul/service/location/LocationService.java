package com.contaazul.service.location;

import com.contaazul.service.location.model.DirectionEnum;
import com.contaazul.service.location.model.Movement;
import com.contaazul.service.location.model.OrientationEnum;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LocationService {

    private int x = 0;
    private int y = 0;

    private OrientationEnum currentOrientation = OrientationEnum.NORTH;
    private static final OrientationEnum[] ORIENTATIONS = OrientationEnum.values();

    private final Movement left = () -> currentOrientation = ORIENTATIONS[currentOrientation.ordinal() - 1 < 0 ? ORIENTATIONS.length - 1 : currentOrientation.ordinal() - 1];
    private final Movement right = () -> currentOrientation = ORIENTATIONS[currentOrientation.ordinal() + 1 > ORIENTATIONS.length - 1 ? 0 : currentOrientation.ordinal() + 1];
    private final Movement forward = this::moveForward;

    public String current() {
        return String.format("(%s, %s, %s)", x, y, currentOrientation);
    }

    public void performMovements(String movements) {
        Arrays.stream(movements.split(""))
                .map(DirectionEnum::lookup)
                .forEach(this::move);
    }

    private void move(DirectionEnum direction) {
        switch (direction) {
            case L:
                left.move();
                break;
            case R:
                right.move();
                break;
            case M:
                forward.move();
                break;
        }
    }

    private void moveForward() {
        switch (currentOrientation) {
            case NORTH:
                setY(y + 1);
                break;
            case EAST:
                setX(x + 1);
                break;
            case SOUTH:
                setY(y - 1);
                break;
            case WEST:
                setX(x - 1);
                break;
        }
    }

    private void setY(int y) {
        int maxY = 5;
        if (y < 0 || y > maxY) {
            throw new IllegalArgumentException("Posição inválida");
        }
        this.y = y;
    }

    private void setX(int x) {
        int maxX = 5;
        if (x < 0 || x > maxX) {
            throw new IllegalArgumentException("Posição inválida");
        }
        this.x = x;
    }
}
