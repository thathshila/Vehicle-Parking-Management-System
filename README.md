# Vehicle-Parking-Management-System

 ## 🔗 Postman Collection
{
	"info": {
		"_postman_id": "094a4cd9-7ff7-4a5d-ae33-8694909598b5",
		"name": "VehicleParkingSystem",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "40383381"
	},
	"item": [
		{
			"name": "ParkingSpaceService",
			"item": [
				{
					"name": "getAll",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces"
							]
						}
					},
					"response": []
				},
				{
					"name": "save",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"spaceNumber\": \"A101\",\r\n  \"location\": \"Basement Level 1\",\r\n  \"zone\": \"Z1\",\r\n  \"city\": \"Colombo\",\r\n  \"status\": \"AVAILABLE\",\r\n  \"type\": \"REGULAR\",\r\n  \"hourlyRate\": 150.0,\r\n  \"ownerId\": \"owner123\",\r\n  \"currentVehicleId\": null\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get parking space by ID",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get parking space by space number",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/number/A101",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"number",
								"A101"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get all available spaces",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/available",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"available"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get spaces by city",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/city/Colombo",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"city",
								"Colombo"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get spaces by zone",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/zone/Z1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"zone",
								"Z1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get spaces by owner ID",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/owner/OWNER123",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"owner",
								"OWNER123"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get available spaces by city",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/available/city/Colombo",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"available",
								"city",
								"Colombo"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get available spaces by zone",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/available/zone/Z1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"available",
								"zone",
								"Z1"
							]
						}
					},
					"response": []
				},
				{
					"name": "update",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"spaceNumber\": \"PARK-105\",\r\n  \"location\": \"Lot A\",\r\n  \"zone\": \"A1\",\r\n  \"city\": \"Colombo\",\r\n  \"type\": \"LARGE\",\r\n  \"hourlyRate\": 150.0,\r\n  \"ownerId\": \"OWNER123\"\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Reserve a space for a vehicle",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "\r\n{\r\n  \"vehicleId\": \"VEH-200\"\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/1/reserve",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"1",
								"reserve"
							]
						}
					},
					"response": []
				},
				{
					"name": "Occupy a space with a vehicle",
					"request": {
						"method": "GET",
						"header": []
					},
					"response": []
				},
				{
					"name": "Release a space",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/1/release",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"1",
								"release"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get parking stats by city",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": ""
						},
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/stats/city/Colombo",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"stats",
								"city",
								"Colombo"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get the parking space by vehicle ID",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8081/parking-space-service/api/parking/spaces/vehicle/2",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8081",
							"path": [
								"parking-space-service",
								"api",
								"parking",
								"spaces",
								"vehicle",
								"2"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "UserService",
			"item": [
				{
					"name": "user",
					"item": [
						{
							"name": "save",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n  \"username\": \"john_doe\",\n  \"email\": \"john@example.com\",\n  \"password\": \"SecurePass123!\",\n  \"firstName\": \"John\",\n  \"lastName\": \"Doe\",\n  \"phoneNumber\": \"+94771234567\",\n  \"role\": \"DRIVER\",\n  \"address\": \"123 Main Street\",\n  \"city\": \"Colombo\",\n  \"state\": \"Western\",\n  \"zipCode\": \"10100\"\n}\n",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users"
									]
								}
							},
							"response": []
						},
						{
							"name": "authenticate",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"username\": \"owner123\",\r\n  \"password\": \"OwnerPass123!\"\r\n}\r\n",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/authenticate",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"authenticate"
									]
								}
							},
							"response": []
						},
						{
							"name": "getAll",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\n  \"username\": \"john_doe\",\n  \"email\": \"john@example.com\",\n  \"password\": \"SecurePass123!\",\n  \"firstName\": \"John\",\n  \"lastName\": \"Doe\",\n  \"phoneNumber\": \"+94771234567\",\n  \"role\": \"DRIVER\",\n  \"address\": \"123 Main Street\",\n  \"city\": \"Colombo\",\n  \"state\": \"Western\",\n  \"zipCode\": \"10100\"\n}\n"
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users"
									]
								}
							},
							"response": []
						},
						{
							"name": "update",
							"request": {
								"method": "PUT",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "// {\r\n//   \"username\": \"john_doe\",\r\n//   \"email\": \"john@gmail.com\",\r\n//   \"password\": \"SecurePass123!\",\r\n//   \"firstName\": \"John\",\r\n//   \"lastName\": \"Doe\",\r\n//   \"phoneNumber\": \"+94771234567\",\r\n//   \"role\": \"DRIVER\",\r\n//   \"address\": \"123 Main Street\",\r\n//   \"city\": \"Colombo\",\r\n//   \"state\": \"Western\",\r\n//   \"zipCode\": \"10100\"\r\n// }\r\n{\r\n      \"username\": \"owner123\",\r\n        \"email\": \"owner@gmail.com\",\r\n        \"password\": \"OwnerPass123!\",\r\n        \"firstName\": \"Thathshila\",\r\n        \"lastName\": \"Ashanganie\",\r\n        \"phoneNumber\": \"+94770000000\",\r\n        \"role\": \"PARKING_OWNER\",\r\n        \"address\": \"456 Business Road\",\r\n        \"city\": \"Kandy\",\r\n        \"state\": \"Central\",\r\n        \"zipCode\": \"20000\",\r\n        \"active\": true,\r\n        \"lastLoginTime\": \"2025-06-09T15:57:30.580826\",\r\n        \"createdAt\": \"2025-06-09T15:49:38.263461\",\r\n        \"updatedAt\": \"2025-06-09T15:57:30.662248\"\r\n}\r\n",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/2",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"2"
									]
								}
							},
							"response": []
						},
						{
							"name": "delete",
							"request": {
								"method": "GET",
								"header": []
							},
							"response": []
						},
						{
							"name": "GetUserById",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/username/janedoe456",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"username",
										"janedoe456"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get User by Username",
							"request": {
								"method": "GET",
								"header": []
							},
							"response": []
						},
						{
							"name": "Get User by Email",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/email/janedoe@gmail.com",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"email",
										"janedoe@gmail.com"
									]
								}
							},
							"response": []
						},
						{
							"name": "Change Password",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"oldPassword\": \"JaneDoe@123\",\r\n  \"newPassword\": \"Jane@123\"\r\n}\r\n",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/2/change-password",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"2",
										"change-password"
									]
								}
							},
							"response": []
						},
						{
							"name": "Deactivate User",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"oldPassword\": \"JaneDoe@123\",\r\n  \"newPassword\": \"Jane@123\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/2/deactivate",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"2",
										"deactivate"
									]
								}
							},
							"response": []
						},
						{
							"name": "Activate User",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"oldPassword\": \"JaneDoe@123\",\r\n  \"newPassword\": \"Jane@123\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/2/activate",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"2",
										"activate"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Users by Role",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"oldPassword\": \"JaneDoe@123\",\r\n  \"newPassword\": \"Jane@123\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/role/PARKING_OWNER",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"role",
										"PARKING_OWNER"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Users by City",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"oldPassword\": \"JaneDoe@123\",\r\n  \"newPassword\": \"Jane@123\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/city/kandy",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"city",
										"kandy"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Users by Status",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"oldPassword\": \"JaneDoe@123\",\r\n  \"newPassword\": \"Jane@123\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/status/ACTIVE",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"status",
										"ACTIVE"
									]
								}
							},
							"response": []
						}
					]
				},
				{
					"name": "Booking-History",
					"item": [
						{
							"name": "create",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"userId\": 2,\r\n  \"vehicleId\": \"2\",\r\n  \"parkingSpaceId\": \"2\",\r\n  \"startTime\": \"2025-06-24T08:00:00\",\r\n  \"endTime\": null,\r\n  \"status\": \"ACTIVE\"\r\n}\r\n",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/bookings",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"bookings"
									]
								}
							},
							"response": []
						},
						{
							"name": "getAll",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"userId\": 2,\r\n  \"vehicleId\": \"2\",\r\n  \"parkingSpaceId\": \"2\",\r\n  \"startTime\": \"2025-06-24T08:00:00\",\r\n  \"endTime\": null,\r\n  \"status\": \"ACTIVE\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/bookings",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"bookings"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Booking by ID",
							"request": {
								"method": "GET",
								"header": []
							},
							"response": []
						},
						{
							"name": "Get Bookings by User",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/bookings/user/2",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"bookings",
										"user",
										"2"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Bookings by Vehicle",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/bookings/vehicle/2",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"bookings",
										"vehicle",
										"2"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Bookings by Parking Space",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/bookings/parking-space/2",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"bookings",
										"parking-space",
										"2"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Active Bookings",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/bookings/active",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"bookings",
										"active"
									]
								}
							},
							"response": []
						},
						{
							"name": "Complete a Booking",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"endTime\": \"2025-06-24T10:00:00\"\r\n}\r\n",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/bookings/1/complete",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"bookings",
										"1",
										"complete"
									]
								}
							},
							"response": []
						},
						{
							"name": "Cancel a Booking",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"endTime\": \"2025-06-24T10:00:00\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/bookings/1/cancel",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"bookings",
										"1",
										"cancel"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get User Booking Stats",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"endTime\": \"2025-06-24T10:00:00\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8083/user-service/api/users/bookings/user/2/stats",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8083",
									"path": [
										"user-service",
										"api",
										"users",
										"bookings",
										"user",
										"2",
										"stats"
									]
								}
							},
							"response": []
						}
					]
				}
			]
		},
		{
			"name": "VehicleService",
			"item": [
				{
					"name": "getAll",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles"
							]
						}
					},
					"response": []
				},
				{
					"name": "save",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"licensePlate\": \"DEF-5679\",\r\n  \"make\": \"Honda\",\r\n  \"model\": \"Civic\",\r\n  \"color\": \"Blue\",\r\n  \"year\": 2022,\r\n  \"type\": \"CAR\",\r\n  \"ownerId\": \"2\",\r\n  \"status\": \"PARKED_OUTSIDE\",\r\n  \"currentParkingSpaceId\": null,\r\n  \"entryTime\": \"2025-06-11T09:00:00\",\r\n  \"exitTime\": null\r\n}\r\n\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles"
							]
						}
					},
					"response": []
				},
				{
					"name": "delete",
					"request": {
						"method": "DELETE",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles/2",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles",
								"2"
							]
						}
					},
					"response": []
				},
				{
					"name": "update",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"licensePlate\": \"DEF-5685\",\r\n  \"make\": \"Honda\",\r\n  \"model\": \"Civic\",\r\n  \"color\": \"Blue\",\r\n  \"year\": 2022,\r\n  \"type\": \"CAR\",\r\n  \"ownerId\": \"2\",\r\n  \"status\": \"PARKED_OUTSIDE\",\r\n  \"currentParkingSpaceId\": null,\r\n  \"entryTime\": \"2025-06-11T09:00:00\",\r\n  \"exitTime\": null\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "getVehicleOwnerById",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"licensePlate\": \"DEF-5679\",\r\n  \"make\": \"Honda\",\r\n  \"model\": \"Civic\",\r\n  \"color\": \"Blue\",\r\n  \"year\": 2022,\r\n  \"type\": \"CAR\",\r\n  \"ownerId\": \"2\",\r\n  \"status\": \"PARKED_OUTSIDE\",\r\n  \"currentParkingSpaceId\": null,\r\n  \"entryTime\": \"2025-06-11T09:00:00\",\r\n  \"exitTime\": null\r\n}\r\n\r\n"
						},
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles/owner/4",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles",
								"owner",
								"4"
							]
						}
					},
					"response": []
				},
				{
					"name": "entry",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"parkingSpaceId\": \"PARK-101\"\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles/1/entry",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles",
								"1",
								"entry"
							]
						}
					},
					"response": []
				},
				{
					"name": "exit",
					"request": {
						"method": "POST",
						"header": [],
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles/1/exit",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles",
								"1",
								"exit"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get Vehicle by ID",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles/1",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles",
								"1"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get Vehicle by License Plate",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles/license/DEF-5679",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles",
								"license",
								"DEF-5679"
							]
						}
					},
					"response": []
				},
				{
					"name": "Get Vehicles by Type",
					"request": {
						"method": "GET",
						"header": [],
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles/type/CAR",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles",
								"type",
								"CAR"
							]
						}
					},
					"response": []
				},
				{
					"name": "Reserve Parking Space",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"parkingSpaceId\": \"space001\"\r\n}\r\n",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles/1/reserve",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles",
								"1",
								"reserve"
							]
						}
					},
					"response": []
				},
				{
					"name": "Find Vehicle in Parking Space",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"parkingSpaceId\": \"space001\"\r\n}\r\n"
						},
						"url": {
							"raw": "http://localhost:8084/vehicle-service/api/vehicles/parking-space/space001",
							"protocol": "http",
							"host": [
								"localhost"
							],
							"port": "8084",
							"path": [
								"vehicle-service",
								"api",
								"vehicles",
								"parking-space",
								"space001"
							]
						}
					},
					"response": []
				}
			]
		},
		{
			"name": "PaymentService",
			"item": [
				{
					"name": "Payment",
					"item": [
						{
							"name": "create",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments"
									]
								}
							},
							"response": []
						},
						{
							"name": "processPayment",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"cardNumber\": \"4111111111111111\",\r\n  \"cardHolderName\": \"John Doe\",\r\n  \"expiryDate\": \"12/26\",\r\n  \"cvv\": \"123\"\r\n}\r\n",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/1/process",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"1",
										"process"
									]
								}
							},
							"response": []
						},
						{
							"name": "getAll",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments"
									]
								}
							},
							"response": []
						},
						{
							"name": "GET Payment by ID",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/1",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"1"
									]
								}
							},
							"response": []
						},
						{
							"name": "GET Payment by Transaction ID",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/transaction/TXN123456",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"transaction",
										"TXN123456"
									]
								}
							},
							"response": []
						},
						{
							"name": "GET Payments by User ID",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/user/2",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"user",
										"2"
									]
								}
							},
							"response": []
						},
						{
							"name": "GET Payments by Status",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/status/COMPLETED",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"status",
										"COMPLETED"
									]
								}
							},
							"response": []
						},
						{
							"name": "Refund Payment",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/1/refund",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"1",
										"refund"
									]
								}
							},
							"response": []
						},
						{
							"name": "Cancel Payment",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/1/cancel",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"1",
										"cancel"
									]
								}
							},
							"response": []
						},
						{
							"name": "GET Total Revenue",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/revenue/total",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"revenue",
										"total"
									]
								}
							},
							"response": []
						},
						{
							"name": "GET User's Total Payments",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/user/2/total",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"user",
										"2",
										"total"
									]
								}
							},
							"response": []
						},
						{
							"name": "GET User Payment Stats",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"transactionId\": \"TXN123456\",\r\n  \"userId\": \"2\",\r\n  \"bookingId\": \"B001\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"amount\": 500.0,\r\n  \"paymentMethod\": \"CREDIT_CARD\"\r\n}\r\n"
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/user/2/stats",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"user",
										"2",
										"stats"
									]
								}
							},
							"response": []
						},
						{
							"name": "Validate Payment Data",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"cardNumber\": \"4111111111111111\",\r\n  \"expiryDate\": \"12/26\",\r\n  \"cvv\": \"123\"\r\n}\r\n\r\n",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/validate",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"validate"
									]
								}
							},
							"response": []
						}
					]
				},
				{
					"name": "Receipt",
					"item": [
						{
							"name": "Get All Receipts",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/receipts",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"receipts"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Receipt by ID",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8082/payment-service/api/payments/receipts/1",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8082",
									"path": [
										"payment-service",
										"api",
										"payments",
										"receipts",
										"1"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Receipt by Receipt Number",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8082/payment-service/api/payments/receipts/number/REC-000123",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8082",
									"path": [
										"payment-service",
										"api",
										"payments",
										"receipts",
										"number",
										"REC-000123"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Receipt by Payment ID",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8082/payment-service/api/payments/receipts/payment/1",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8082",
									"path": [
										"payment-service",
										"api",
										"payments",
										"receipts",
										"payment",
										"1"
									]
								}
							},
							"response": []
						},
						{
							"name": "Get Receipts by User ID",
							"request": {
								"method": "GET",
								"header": [],
								"url": {
									"raw": "http://localhost:8082/payment-service/api/payments/receipts/user/1",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8082",
									"path": [
										"payment-service",
										"api",
										"payments",
										"receipts",
										"user",
										"1"
									]
								}
							},
							"response": []
						},
						{
							"name": "Generate Receipt",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"paymentId\": \"1\",\r\n  \"userId\": \"2\",\r\n  \"parkingSpaceId\": \"1\",\r\n  \"vehicleId\": \"1\",\r\n  \"amount\": 100.0,\r\n  \"hourlyRate\": 10.0,\r\n  \"startTime\": \"2025-06-25T08:00:00\",\r\n  \"endTime\": \"2025-06-25T10:30:00\"\r\n}\r\n",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8080/payment-service/api/payments/receipts/generate",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"payment-service",
										"api",
										"payments",
										"receipts",
										"generate"
									]
								}
							},
							"response": []
						}
					]
				}
			]
		}
	]
}[VehicleParkingSystem.postman_collection.json](https://github.com/user-attachments/files/20902441/VehicleParkingSystem.postman_collection.json)
(./postman_collection.json)

## 📊 Eureka Dashboard
![Screenshot 2025-06-25 130956](https://github.com/user-attachments/assets/4ed6f375-7930-4629-b955-a4d8bc8d95b7)
(./docs/screenshots/eureka_dashboard.png)
