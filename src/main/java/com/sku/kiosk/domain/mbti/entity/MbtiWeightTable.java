/* 
 * Copyright (c) SKU K-IO-SK 
 */
package com.sku.kiosk.domain.event.entity;

import static com.sku.kiosk.domain.event.entity.EventClassification.CLASSICAL;
import static com.sku.kiosk.domain.event.entity.EventClassification.CONCERT;
import static com.sku.kiosk.domain.event.entity.EventClassification.DANCE;
import static com.sku.kiosk.domain.event.entity.EventClassification.EDU_EXPERIENCE;
import static com.sku.kiosk.domain.event.entity.EventClassification.ETC;
import static com.sku.kiosk.domain.event.entity.EventClassification.EXHIBITION_ART;
import static com.sku.kiosk.domain.event.entity.EventClassification.FEST_ART;
import static com.sku.kiosk.domain.event.entity.EventClassification.FEST_CIVIC;
import static com.sku.kiosk.domain.event.entity.EventClassification.FEST_ETC;
import static com.sku.kiosk.domain.event.entity.EventClassification.FEST_HISTORY;
import static com.sku.kiosk.domain.event.entity.EventClassification.FEST_NATURE;
import static com.sku.kiosk.domain.event.entity.EventClassification.KOREAN_TRADITIONAL;
import static com.sku.kiosk.domain.event.entity.EventClassification.MOVIE;
import static com.sku.kiosk.domain.event.entity.EventClassification.MUSICAL_OPERA;
import static com.sku.kiosk.domain.event.entity.EventClassification.PLAY;
import static com.sku.kiosk.domain.event.entity.EventClassification.RECITAL;

import java.util.Map;

public final class MbtiWeightTable {
  public static final Map<EventClassification, Integer[]> weightEI =
      Map.ofEntries(
          Map.entry(EDU_EXPERIENCE, new Integer[] {2, 1}),
          Map.entry(KOREAN_TRADITIONAL, new Integer[] {1, 2}),
          Map.entry(ETC, new Integer[] {1, 1}),
          Map.entry(RECITAL, new Integer[] {1, 2}),
          Map.entry(DANCE, new Integer[] {2, 1}),
          Map.entry(MUSICAL_OPERA, new Integer[] {2, 2}),
          Map.entry(PLAY, new Integer[] {1, 2}),
          Map.entry(MOVIE, new Integer[] {1, 3}),
          Map.entry(EXHIBITION_ART, new Integer[] {0, 3}),
          Map.entry(FEST_ETC, new Integer[] {3, 0}),
          Map.entry(FEST_ART, new Integer[] {3, 0}),
          Map.entry(FEST_CIVIC, new Integer[] {3, 0}),
          Map.entry(FEST_NATURE, new Integer[] {3, 0}),
          Map.entry(FEST_HISTORY, new Integer[] {3, 0}),
          Map.entry(CONCERT, new Integer[] {3, 1}),
          Map.entry(CLASSICAL, new Integer[] {1, 3}));

  public static final Map<EventClassification, Integer[]> weightSN =
      Map.ofEntries(
          Map.entry(EDU_EXPERIENCE, new Integer[] {3, 1}),
          Map.entry(KOREAN_TRADITIONAL, new Integer[] {3, 1}),
          Map.entry(ETC, new Integer[] {1, 1}),
          Map.entry(RECITAL, new Integer[] {2, 3}),
          Map.entry(DANCE, new Integer[] {2, 2}),
          Map.entry(MUSICAL_OPERA, new Integer[] {2, 3}),
          Map.entry(PLAY, new Integer[] {1, 3}),
          Map.entry(MOVIE, new Integer[] {1, 3}),
          Map.entry(EXHIBITION_ART, new Integer[] {1, 3}),
          Map.entry(FEST_ETC, new Integer[] {2, 2}),
          Map.entry(FEST_ART, new Integer[] {2, 2}),
          Map.entry(FEST_CIVIC, new Integer[] {2, 2}),
          Map.entry(FEST_NATURE, new Integer[] {3, 1}),
          Map.entry(FEST_HISTORY, new Integer[] {3, 1}),
          Map.entry(CONCERT, new Integer[] {2, 2}),
          Map.entry(CLASSICAL, new Integer[] {2, 3}));

  public static final Map<EventClassification, Integer[]> weightTF =
      Map.ofEntries(
          Map.entry(EDU_EXPERIENCE, new Integer[] {2, 2}),
          Map.entry(KOREAN_TRADITIONAL, new Integer[] {2, 2}),
          Map.entry(ETC, new Integer[] {1, 1}),
          Map.entry(RECITAL, new Integer[] {3, 2}),
          Map.entry(DANCE, new Integer[] {1, 3}),
          Map.entry(MUSICAL_OPERA, new Integer[] {2, 3}),
          Map.entry(PLAY, new Integer[] {3, 3}),
          Map.entry(MOVIE, new Integer[] {3, 2}),
          Map.entry(EXHIBITION_ART, new Integer[] {3, 2}),
          Map.entry(FEST_ETC, new Integer[] {1, 3}),
          Map.entry(FEST_ART, new Integer[] {1, 3}),
          Map.entry(FEST_CIVIC, new Integer[] {1, 3}),
          Map.entry(FEST_NATURE, new Integer[] {1, 3}),
          Map.entry(FEST_HISTORY, new Integer[] {1, 3}),
          Map.entry(CONCERT, new Integer[] {1, 3}),
          Map.entry(CLASSICAL, new Integer[] {3, 2}));

  public static final Map<EventClassification, Integer[]> weightJP =
      Map.ofEntries(
          Map.entry(EDU_EXPERIENCE, new Integer[] {2, 2}),
          Map.entry(KOREAN_TRADITIONAL, new Integer[] {2, 1}),
          Map.entry(ETC, new Integer[] {1, 2}),
          Map.entry(RECITAL, new Integer[] {3, 1}),
          Map.entry(DANCE, new Integer[] {1, 3}),
          Map.entry(MUSICAL_OPERA, new Integer[] {2, 2}),
          Map.entry(PLAY, new Integer[] {2, 2}),
          Map.entry(MOVIE, new Integer[] {3, 1}),
          Map.entry(EXHIBITION_ART, new Integer[] {3, 1}),
          Map.entry(FEST_ETC, new Integer[] {1, 3}),
          Map.entry(FEST_ART, new Integer[] {1, 3}),
          Map.entry(FEST_CIVIC, new Integer[] {1, 3}),
          Map.entry(FEST_NATURE, new Integer[] {1, 3}),
          Map.entry(FEST_HISTORY, new Integer[] {1, 3}),
          Map.entry(CONCERT, new Integer[] {1, 3}),
          Map.entry(CLASSICAL, new Integer[] {3, 1}));

  public static int calculateScore(String mbti, EventClassification classification) {

    int score = 0;
    char ei = mbti.charAt(0);
    char sn = mbti.charAt(1);
    char tf = mbti.charAt(2);
    char jp = mbti.charAt(3);

    Integer[] eiW = MbtiWeightTable.weightEI.get(classification);
    Integer[] snW = MbtiWeightTable.weightSN.get(classification);
    Integer[] tfW = MbtiWeightTable.weightTF.get(classification);
    Integer[] jpW = MbtiWeightTable.weightJP.get(classification);

    score += (ei == 'E') ? eiW[0] : eiW[1];
    score += (sn == 'S') ? snW[0] : snW[1];
    score += (tf == 'T') ? tfW[0] : tfW[1];
    score += (jp == 'J') ? jpW[0] : jpW[1];

    return score;
  }

  private MbtiWeightTable() {}
}
