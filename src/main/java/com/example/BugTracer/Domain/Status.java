package com.example.BugTracer.Domain;

/**
 * 3 progress status:
 *  done for done
 *  unassigned if task is not assigned
 *  in-progress if task is assigned
 */
public enum Status {
  DONE, UNASSIGNED, IN_PROGRESS;
}
