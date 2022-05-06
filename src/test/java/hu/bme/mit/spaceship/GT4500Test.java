package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockTorpedo;
  private TorpedoStore mockTorpedo2;

  @BeforeEach
  public void init(){
    mockTorpedo = mock(TorpedoStore.class);
    mockTorpedo2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockTorpedo, mockTorpedo2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTorpedo.fire(1)).thenReturn(true);
    when(mockTorpedo2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    verify(mockTorpedo, times(1)).fire(1);
    verify(mockTorpedo2, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockTorpedo.getTorpedoCount()).thenReturn(100);
    when(mockTorpedo2.getTorpedoCount()).thenReturn(100);
    when(mockTorpedo.fire(100)).thenReturn(true);
    when(mockTorpedo2.fire(100)).thenReturn(true);
    when(mockTorpedo.isEmpty()).thenReturn(false);
    when(mockTorpedo2.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

    verify(mockTorpedo, times(1)).fire(100);
    verify(mockTorpedo2, times(1)).fire(100);
  }

  @Test
  public void fireTorpedo_Single_Fail(){
    // Arrange
    when(mockTorpedo.isEmpty()).thenReturn(true);
    when(mockTorpedo2.isEmpty()).thenReturn(true);
    when(mockTorpedo.fire(1)).thenReturn(false);
    when(mockTorpedo2.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);

    verify(mockTorpedo, times(0)).fire(1);
    verify(mockTorpedo2, times(0)).fire(1);
  }

}
